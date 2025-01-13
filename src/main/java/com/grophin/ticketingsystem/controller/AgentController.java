package com.grophin.ticketingsystem.controller;

import com.grophin.ticketingsystem.dto.request.AssignRequest;
import com.grophin.ticketingsystem.dto.request.CreateRequest;

import com.grophin.ticketingsystem.dto.response.AssignResponse;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;
import com.grophin.ticketingsystem.services.AgentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    @Autowired
    AgentServiceInterface agentServiceInterface;

    @PostMapping(
            path = "/add",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<CreateResponse> addAgent(@Valid @RequestBody CreateRequest createRequest)throws Exception{
        CreateResponse agentCreateResponse = agentServiceInterface.addUserAgent(createRequest);
        if(agentCreateResponse.getStatus() == 1){
            return new ResponseEntity<>(agentCreateResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(agentCreateResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(
            path="/assign",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<AssignResponse> assignAgent(@Valid @RequestBody AssignRequest assignRequest) throws Exception{
        AssignResponse assignResponse = agentServiceInterface.assignAgent(assignRequest);
        if(assignResponse.getStatus() == 1){
            return new ResponseEntity<>(assignResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(assignResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(
            path = "/fetch/{email}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<AgentDetails> getAgentDetails(@PathVariable String email) throws Exception{
        return new ResponseEntity<>(agentServiceInterface.getAgentDetails(email), HttpStatus.OK);
    }

    @GetMapping(
            path="/getAgents",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<List<String>> getAgents()throws Exception{
        return new ResponseEntity<>(agentServiceInterface.getAvailableAgents(),HttpStatus.OK);
    }
}
