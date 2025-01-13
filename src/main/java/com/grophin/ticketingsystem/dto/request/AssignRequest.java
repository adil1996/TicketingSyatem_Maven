package com.grophin.ticketingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AssignRequest {

    @NotNull
    @NotEmpty
    String ticketId;

    @NotNull
    @NotEmpty
    String agentId;
}
