package com.grophin.ticketingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmailRequest {

    @NotEmpty
    @NotNull
    @Email(message = "From Email should be valid")
    String from;

    @NotEmpty
    @NotNull
    @Email(message = "From Email should be valid")
    String to;

    @NotEmpty
    @NotNull
    String subject;

    @NotEmpty
    @NotNull
    String content;

    @NotEmpty
    @NotNull
    String apiKey;

}
