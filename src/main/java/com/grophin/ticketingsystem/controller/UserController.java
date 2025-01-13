package com.grophin.ticketingsystem.controller;

import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.AgentDetails;
import com.grophin.ticketingsystem.models.UserDetails;
import com.grophin.ticketingsystem.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserServiceInterface agentServiceInterface;

    @PostMapping(
            path = "/add",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<CreateResponse> addAgent(@Valid @RequestBody CreateRequest agentCreateRequest)throws Exception{
        CreateResponse agentCreateResponse = agentServiceInterface.addUserAgent(agentCreateRequest);
        if(agentCreateResponse.getStatus() == 1){
            return new ResponseEntity<>(agentCreateResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(agentCreateResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(
            path = "/fetch/{email}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<UserDetails> getAgentDetails(@PathVariable String email) throws Exception{
        return new ResponseEntity<>(agentServiceInterface.getUserDetails(email), HttpStatus.OK);
    }
}
