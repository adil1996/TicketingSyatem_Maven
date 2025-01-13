package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.TicketCreateRequest;
import com.grophin.ticketingsystem.dto.request.UpdateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.dto.response.UpdateResponse;
import com.grophin.ticketingsystem.models.TicketDetails;

import java.util.List;

public interface TicketServiceInterface {

    CreateResponse addTicket(TicketCreateRequest ticketCreateRequest) throws Exception;
    List<TicketDetails> fetchAllTicket() throws Exception;
    TicketDetails fetchByTicketId(String ticketId) throws Exception;
    List<TicketDetails> fetchByAgent(String agentId) throws Exception;
    List<TicketDetails> fetchByCustomer(String customer) throws Exception;
    List<TicketDetails> fetchByStatus(String status) throws Exception;
    UpdateResponse updateDetails(UpdateRequest updateRequest) throws Exception;
    UpdateResponse updateStatus(UpdateRequest updateRequest) throws Exception;
    UpdateResponse updateResponse(UpdateRequest updateRequest) throws Exception;
    UpdateResponse deleteTicket(String ticketId) throws Exception;
}
