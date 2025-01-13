package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.AssignRequest;
import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.response.AssignResponse;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;
import com.grophin.ticketingsystem.models.AssignedAgent;
import com.grophin.ticketingsystem.repos.AgentsDetailsRepo;
import com.grophin.ticketingsystem.repos.AssignedAgentRepo;
import com.grophin.ticketingsystem.repos.TicketStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentServiceInterface {

    AgentsDetailsRepo agentsDetailsRepo;
    AssignedAgentRepo assignedAgentRepo;

    @Autowired
    TicketStatusRepo ticketStatusRepo;

    @Autowired
    public AgentServiceImpl(AgentsDetailsRepo agentsDetailsRepo, AssignedAgentRepo assignedAgentRepo){
        this.agentsDetailsRepo = agentsDetailsRepo;
        this.assignedAgentRepo = assignedAgentRepo;
    }

    @Override
    public CreateResponse addUserAgent(CreateRequest createRequest) throws Exception {
        CreateResponse agentCreateResponse = new CreateResponse();
        try{
            AgentDetails agentDetails = new AgentDetails();
            agentDetails.setAgentId(createRequest.getId());
            agentDetails.setAgentName(createRequest.getName());
            agentDetails.setAgentEmail(createRequest.getEmail());
            agentDetails.setAgentContactNo(createRequest.getContactNo());
            agentDetails.setAddress(createRequest.getAddress());
            agentDetails.setPassword(createRequest.getPassword());
            this.agentsDetailsRepo.save(agentDetails);

            agentCreateResponse.setId(createRequest.getId());
            agentCreateResponse.setName(createRequest.getName());
            agentCreateResponse.setStatus(1);
            agentCreateResponse.setMessage("Agent Added Successfully");

        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            agentCreateResponse.setStatus(0);
            agentCreateResponse.setMessage("Exception while adding\n"+stringWriter);
        }

        return agentCreateResponse;

    }

    @Override
    public AssignResponse assignAgent(AssignRequest assignRequest) throws Exception {
        AssignResponse assignResponse = new AssignResponse();
        try{
            AssignedAgent assignedAgent = new AssignedAgent();
            assignedAgent.setAgentId(assignRequest.getAgentId());
            assignedAgent.setTicketId(assignRequest.getTicketId());
            this.assignedAgentRepo.save(assignedAgent);

            assignResponse.setAgentId(assignRequest.getAgentId());
            assignResponse.setTicketId(assignRequest.getTicketId());
            assignResponse.setStatus(1);
            assignResponse.setMessage("Successfully assigned");
        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            assignResponse.setStatus(0);
            assignResponse.setMessage("Exception while adding\n"+stringWriter);
        }
        return assignResponse;
    }

    @Override
    public AgentDetails getAgentDetails(String email) throws Exception {
        return this.agentsDetailsRepo.findByAgentEmail(email);
    }

    @Override
    public List<String> getAvailableAgents() throws Exception {
        List<String> agentList = new ArrayList<>();
        try{
            HashMap<String,Integer> map = new HashMap<>();
            List<AssignedAgent> assignedAgents = this.assignedAgentRepo.findAll();
            for(AssignedAgent assignedAgent: assignedAgents){
                if(assignedAgent.getAgentId() != null){
                String val = this.ticketStatusRepo.findByTicketId(assignedAgent.getTicketId()).getStatus();
                if(val.equalsIgnoreCase("Open")){
                    if(map.containsKey(assignedAgent.getAgentId())){
                        map.put(assignedAgent.getAgentId(),map.get(assignedAgent.getAgentId())+1);
                    }else{
                        map.put(assignedAgent.getAgentId(),1);
                    }
                }
            }}
            int min = Integer.MAX_VALUE;
            for(String key: map.keySet()){
                int value =map.get(key);
                if(value <min){
                    agentList = new ArrayList<>();
                    min = value;
                    agentList.add(key);
                }
                else if(value == min){
                    agentList.add(key);
                }

            }

            List<AgentDetails> agentDetails = this.agentsDetailsRepo.findAll();
            int i=0;
            for(AgentDetails agent: agentDetails){
                if(!agentList.contains(agent.getAgentId())){
                    if(i==0){
                        agentList = new ArrayList<>();
                        i=1;
                    }
                    agentList.add(agent.getAgentId());
                }
            }

        }
        catch (Exception ex){
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            System.out.println(stringWriter);
        }

        return agentList;
    }
}
