package com.grophin.ticketingsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Valid
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CreateRequest {

    @NotNull
    @NotEmpty
    String id;

    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    @javax.validation.constraints.Email(message = "Email should be the valid address")
    String Email;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{10}", message = "Contact should be of 10 digits an should contains numbers only.")
    String contactNo;

    @NotNull
    @NotEmpty
    String address;

    @NotNull
    @NotEmpty
    String password;
}
