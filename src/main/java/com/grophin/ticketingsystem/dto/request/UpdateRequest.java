package com.grophin.ticketingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;

@Valid
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateRequest {

    @NotEmpty
    @NotNull
    String ticketId;

    @NotNull
    @NotEmpty
    @Size(min = 1,message = "At least one field is needed")
    HashMap<String,String> dataMap;
}
