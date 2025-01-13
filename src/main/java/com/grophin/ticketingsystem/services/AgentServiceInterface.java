package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.AssignRequest;
import com.grophin.ticketingsystem.dto.request.CreateRequest;

import com.grophin.ticketingsystem.dto.response.AssignResponse;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;

import java.util.List;

public interface AgentServiceInterface {

    CreateResponse addUserAgent(CreateRequest createRequest) throws Exception;
    AssignResponse assignAgent(AssignRequest assignRequest) throws Exception;
    AgentDetails getAgentDetails(String email) throws Exception;
    List<String> getAvailableAgents() throws Exception;
}

