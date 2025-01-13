package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;
import com.grophin.ticketingsystem.models.UserDetails;
import com.grophin.ticketingsystem.repos.AgentsDetailsRepo;
import com.grophin.ticketingsystem.repos.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class UserServiceImpl implements UserServiceInterface {

    UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserServiceImpl(UserDetailsRepo userDetailsRepo){
        this.userDetailsRepo = userDetailsRepo;
    }

    @Override
    public CreateResponse addUserAgent(CreateRequest createRequest) throws Exception {
        CreateResponse agentCreateResponse = new CreateResponse();
        try{
            UserDetails userDetails = new UserDetails();
            userDetails.setUserId(createRequest.getId());
            userDetails.setUserName(createRequest.getName());
            userDetails.setUsertEmail(createRequest.getEmail());
            userDetails.setUserContactNo(createRequest.getContactNo());
            userDetails.setUserAddress(createRequest.getAddress());
            userDetails.setPassword(createRequest.getPassword());
            this.userDetailsRepo.save(userDetails);

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
    public UserDetails getUserDetails(String email) throws Exception {
        return this.userDetailsRepo.findByUsertEmail(email);
    }
}
