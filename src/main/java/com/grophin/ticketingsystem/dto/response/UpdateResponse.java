package com.grophin.ticketingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import java.util.HashMap;


@Data
public class UpdateResponse {

    String ticketId;
    HashMap<String,String> updatedFields;
    String message;
}
