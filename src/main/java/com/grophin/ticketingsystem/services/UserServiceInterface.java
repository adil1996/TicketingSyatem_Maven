package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.CreateRequest;
import com.grophin.ticketingsystem.dto.response.CreateResponse;
import com.grophin.ticketingsystem.models.UserDetails;

public interface UserServiceInterface {

    CreateResponse addUserAgent(CreateRequest createRequest) throws Exception;
    UserDetails getUserDetails(String email) throws Exception;
}
