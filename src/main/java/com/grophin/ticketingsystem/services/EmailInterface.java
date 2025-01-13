package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.dto.request.EmailRequest;
import com.grophin.ticketingsystem.dto.response.EmailResponse;

public interface EmailInterface {

    EmailResponse sendEmail(EmailRequest emailRequest) throws Exception;
}
