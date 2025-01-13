package com.grophin.ticketingsystem.controller;

import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.request.TicketCreateRequest;
import com.grophin.ticketingsystem.dto.request.UpdateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.dto.response.UpdateResponse;
import com.grophin.ticketingsystem.models.TicketDetails;
import com.grophin.ticketingsystem.services.TicketServiceInterface;
import com.grophin.ticketingsystem.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    @Autowired
    TicketServiceInterface ticketServiceInterface;

    @PostMapping(
            path = "/add",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<CreateResponse> addAgent(@Valid @RequestBody TicketCreateRequest agentCreateRequest)throws Exception{
        CreateResponse agentCreateResponse = ticketServiceInterface.addTicket(agentCreateRequest);
        if(agentCreateResponse.getStatus() == 1){
            return new ResponseEntity<>(agentCreateResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(agentCreateResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(
            path = "/fetchall",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<List<TicketDetails>> fetchAllTicketDetails() throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.fetchAllTicket(),HttpStatus.OK);
    }

    @GetMapping(
            path = "/fetch/{ticketId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<TicketDetails> findByTicketId(@PathVariable String ticketId) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.fetchByTicketId(ticketId),HttpStatus.OK);
    }

    @GetMapping(
            path = "/fetch/agent/{agentId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<List<TicketDetails>> findByAgent(@PathVariable String agentId) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.fetchByAgent(agentId),HttpStatus.OK);
    }

    @GetMapping(
            path = "/fetch/customer/{customer}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<List<TicketDetails>> findByCustomer(@PathVariable String customer) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.fetchByCustomer(customer),HttpStatus.OK);
    }

    @GetMapping(
            path = "/fetch/status/{status}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<List<TicketDetails>> findByStatus(@PathVariable String status) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.fetchByStatus(status),HttpStatus.OK);
    }

    @PutMapping(
            path = "/updatedetails",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<UpdateResponse> updateDetails(@Valid @RequestBody UpdateRequest updateRequest) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.updateDetails(updateRequest),HttpStatus.OK);
    }

    @PutMapping(
            path = "/updatestatus",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<UpdateResponse> updateStatus(@Valid @RequestBody UpdateRequest updateRequest) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.updateStatus(updateRequest),HttpStatus.OK);
    }

    @PutMapping(
            path = "/updateresponse",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    ResponseEntity<UpdateResponse> updateReponse(@Valid @RequestBody UpdateRequest updateRequest) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.updateResponse(updateRequest),HttpStatus.OK);
    }

    @DeleteMapping(
            path="/deleteticket/{ticketId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public ResponseEntity<UpdateResponse> deleteTickets(@PathVariable String ticketId) throws Exception{
        return new ResponseEntity<>(ticketServiceInterface.deleteTicket(ticketId),HttpStatus.OK);
    }

}

