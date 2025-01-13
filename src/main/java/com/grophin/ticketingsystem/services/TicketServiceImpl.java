package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.constants.Constants;
import com.grophin.ticketingsystem.dto.request.TicketCreateRequest;
import com.grophin.ticketingsystem.dto.request.UpdateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.dto.response.UpdateResponse;
import com.grophin.ticketingsystem.models.*;
import com.grophin.ticketingsystem.repos.AssignedAgentRepo;
import com.grophin.ticketingsystem.repos.TicketDetailsRepo;
import com.grophin.ticketingsystem.repos.TicketResposeRepo;
import com.grophin.ticketingsystem.repos.TicketStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketServiceInterface{


    TicketDetailsRepo ticketDetailsRepo;
    TicketStatusRepo ticketStatusRepo;
    AssignedAgentRepo assignedAgentRepo;
    TicketResposeRepo ticketResponseRepo;

    @Autowired
    public TicketServiceImpl(TicketDetailsRepo ticketDetailsRepo, TicketStatusRepo ticketStatusRepo, AssignedAgentRepo assignedAgentRepo, TicketResposeRepo ticketResponseRepo) {
        this.ticketDetailsRepo = ticketDetailsRepo;
        this.ticketStatusRepo = ticketStatusRepo;
        this.assignedAgentRepo = assignedAgentRepo;
        this.ticketResponseRepo = ticketResponseRepo;
    }

    @Override
    public CreateResponse addTicket(TicketCreateRequest ticketCreateRequest) throws Exception {
        CreateResponse agentCreateResponse = new CreateResponse();
        try{
            TicketStatus ticketStatus = new TicketStatus();
            ticketStatus.setTicketId(ticketCreateRequest.getId());
            ticketStatus.setStatus("Open");
            this.ticketStatusRepo.save(ticketStatus);

            AssignedAgent assignedAgent = new AssignedAgent();
            assignedAgent.setTicketId(ticketCreateRequest.getId());
            assignedAgent.setAgentId(null);
            this.assignedAgentRepo.save(assignedAgent);

            TicketResponse ticketResponse = new TicketResponse();
            ticketResponse.setResponse(null);
            ticketResponse.setTicketId(ticketCreateRequest.getId());
            this.ticketResponseRepo.save(ticketResponse);

            TicketDetails ticketDetails = new TicketDetails();
            ticketDetails.setTicketId(ticketCreateRequest.getId());
            ticketDetails.setDescription(ticketCreateRequest.getDescription());
            ticketDetails.setCreateUser(ticketCreateRequest.getUser());
            ticketDetails.setPriority(Integer.parseInt(ticketCreateRequest.getPriority()));
            ticketDetails.setTitle(ticketCreateRequest.getTitle());
            ticketDetails.setType(ticketCreateRequest.getType());
            ticketDetails.setCustomer(ticketCreateRequest.getCustomer());
            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(ticketCreateRequest.getDeadline());
            ticketDetails.setDeadline(date1);
            this.ticketDetailsRepo.save(ticketDetails);

            agentCreateResponse.setId(ticketCreateRequest.getId());
            agentCreateResponse.setName(ticketCreateRequest.getTitle());
            agentCreateResponse.setStatus(1);
            agentCreateResponse.setMessage("Ticket Created Successfully");

        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            agentCreateResponse.setStatus(0);
            agentCreateResponse.setMessage("Exception while creating the ticket: \n"+stringWriter);
        }

        return agentCreateResponse;
    }

    @Override
    public List<TicketDetails> fetchAllTicket() throws Exception {
        List<TicketDetails> tickets = this.ticketDetailsRepo.findAll();
        return tickets;
    }

    @Override
    public TicketDetails fetchByTicketId(String ticketId) throws Exception {
        return this.ticketDetailsRepo.findByTicketId(ticketId);
    }

    @Override
    public List<TicketDetails> fetchByAgent(String agentId) throws Exception {
        List<TicketDetails> ticketDetails = new ArrayList<>();
        List<AssignedAgent> assignedAgents = this.assignedAgentRepo.findByAgentId(agentId);
        assignedAgents.forEach(
                agents->{
                    TicketDetails ticketDetails1 = this.ticketDetailsRepo.findByTicketId(agents.getTicketId());
                    ticketDetails.add(ticketDetails1);
                }
        );
        return ticketDetails;
    }

    @Override
    public List<TicketDetails> fetchByCustomer(String customer) throws Exception {
        return this.ticketDetailsRepo.findByCustomer(customer);
    }

    @Override
    public List<TicketDetails> fetchByStatus(String status) throws Exception {
        List<TicketDetails> ticketDetails = new ArrayList<>();
        List<TicketStatus> ticketStatuses = this.ticketStatusRepo.findByStatus(status);
        ticketStatuses.forEach(
                status1->{
                    TicketDetails ticketDetails1 = this.ticketDetailsRepo.findByTicketId(status1.getTicketId());
                    ticketDetails.add(ticketDetails1);
                }
        );
        return ticketDetails;
    }

    @Override
    public UpdateResponse updateDetails(UpdateRequest updateRequest) throws Exception {

        UpdateResponse updateResponse = new UpdateResponse();
        try{
            TicketDetails ticketDetails = this.ticketDetailsRepo.findByTicketId(updateRequest.getTicketId());

            if(updateRequest.getDataMap().get(Constants.CUSTOMER) != null){
                ticketDetails.setCustomer(updateRequest.getDataMap().get(Constants.CUSTOMER));
            }

            if(updateRequest.getDataMap().get(Constants.DESCRIPTION) != null){
                ticketDetails.setDescription(updateRequest.getDataMap().get(Constants.DESCRIPTION));
            }

            if(updateRequest.getDataMap().get(Constants.TYPE) != null){
                ticketDetails.setType(updateRequest.getDataMap().get(Constants.TYPE));
            }

            if(updateRequest.getDataMap().get(Constants.PRIORITY) != null){
                ticketDetails.setPriority(Integer.parseInt(updateRequest.getDataMap().get(Constants.PRIORITY)));
            }

            if(updateRequest.getDataMap().get(Constants.TITLE) != null){
                ticketDetails.setTitle(updateRequest.getDataMap().get(Constants.TITLE));
            }

            if(updateRequest.getDataMap().get(Constants.DEADLINE) != null){
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(updateRequest.getDataMap().get(Constants.DEADLINE));
                ticketDetails.setDeadline(date);
            }

            this.ticketDetailsRepo.save(ticketDetails);
            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Successfully Updated.");
            updateResponse.setUpdatedFields(updateRequest.getDataMap());

        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Exception while updating details\n."+stringWriter);
            updateResponse.setUpdatedFields(updateRequest.getDataMap());
        }
        return updateResponse;
    }

    @Override
    public UpdateResponse updateStatus(UpdateRequest updateRequest) throws Exception {
        UpdateResponse updateResponse = new UpdateResponse();
        try{
            TicketStatus ticketStatus = this.ticketStatusRepo.findByTicketId(updateRequest.getTicketId());
            if(updateRequest.getDataMap().get(Constants.STATUS) != null){
                ticketStatus.setStatus(updateRequest.getDataMap().get(Constants.STATUS));
            }

            this.ticketStatusRepo.save(ticketStatus);

            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Successfully Updated.");
            updateResponse.setUpdatedFields(updateRequest.getDataMap());
        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Exception while updating details\n."+stringWriter);
            updateResponse.setUpdatedFields(updateRequest.getDataMap());
        }

        return updateResponse;
    }

    @Override
    public UpdateResponse updateResponse(UpdateRequest updateRequest) throws Exception {
        UpdateResponse updateResponse = new UpdateResponse();
        try{
            TicketResponse ticketStatus = this.ticketResponseRepo.findByTicketId(updateRequest.getTicketId());
            if(updateRequest.getDataMap().get(Constants.RESPONSE) != null){
                ticketStatus.setResponse(updateRequest.getDataMap().get(Constants.RESPONSE));
            }

            this.ticketResponseRepo.save(ticketStatus);

            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Successfully Updated.");
            updateResponse.setUpdatedFields(updateRequest.getDataMap());
        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            updateResponse.setTicketId(updateRequest.getTicketId());
            updateResponse.setMessage("Exception while updating details\n."+stringWriter);
            updateResponse.setUpdatedFields(updateRequest.getDataMap());
        }

        return updateResponse;
    }

    @Override
    public UpdateResponse deleteTicket(String ticketId) throws Exception {
        UpdateResponse updateResponse = new UpdateResponse();
        try{
            TicketResponse ticketResponse = this.ticketResponseRepo.findByTicketId(ticketId);
            TicketDetails ticketDetails = this.ticketDetailsRepo.findByTicketId(ticketId);
            TicketStatus ticketStatus1 = this.ticketStatusRepo.findByTicketId(ticketId);
            AssignedAgent assignedAgent = this.assignedAgentRepo.findByTicketId(ticketId);

            this.ticketDetailsRepo.delete(ticketDetails);
            this.ticketStatusRepo.delete(ticketStatus1);
            this.ticketResponseRepo.delete(ticketResponse);
            this.assignedAgentRepo.delete(assignedAgent);

            updateResponse.setTicketId(ticketId);
            updateResponse.setMessage("Successfully Deleted.");
            updateResponse.setUpdatedFields(null);
        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            updateResponse.setTicketId(ticketId);
            updateResponse.setMessage("Exception while Deleting\n."+stringWriter);
            updateResponse.setUpdatedFields(null);
        }

        return updateResponse;
    }
}
