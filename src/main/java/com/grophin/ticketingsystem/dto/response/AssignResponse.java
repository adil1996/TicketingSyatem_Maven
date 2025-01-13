package com.grophin.ticketingsystem.dto.response;

import lombok.Data;

@Data
public class AssignResponse {

    String ticketId;
    String agentId;
    int status;
    String message;
}
