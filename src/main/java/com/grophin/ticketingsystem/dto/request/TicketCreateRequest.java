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
public class TicketCreateRequest {

    @NotNull
    @NotEmpty
    String id;

    @NotNull
    @NotEmpty
    String type;

    @NotNull
    String user;

    @NotNull
    @NotEmpty
    String description;

    @NotNull
    @NotEmpty
    String title;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[1-5]$", message = "Priority should be within 5")
    String priority;

    @NotNull
    @NotEmpty
    String customer;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[ /](0[1-9]|1[012])[ /](19|20)\\d\\d$",message = "dealine should be in the fromat of dd/MM/yyyy")
    String deadline;
}
