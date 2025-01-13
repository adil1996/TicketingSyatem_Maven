package com.grophin.ticketingsystem.services;

import com.grophin.ticketingsystem.constants.Constants;
import com.grophin.ticketingsystem.dto.request.EmailRequest;
import com.grophin.ticketingsystem.dto.response.EmailResponse;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Service
public class EmailImpl implements EmailInterface{

    @Override
    public EmailResponse sendEmail(EmailRequest emailRequest) throws Exception {
        Email from = new Email(emailRequest.getFrom());
        String subject = emailRequest.getSubject();
        Email to = new Email(emailRequest.getTo());
        Content content = new Content(Constants.CONTENTTYPE, emailRequest.getContent());
        Mail mail = new Mail(from, subject, to, content);
        EmailResponse emailResponse = new EmailResponse();
        SendGrid sg = new SendGrid(System.getenv(emailRequest.getApiKey()));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint(Constants.ENDPOINT);
            request.setBody(mail.build());
            Response response = sg.api(request);
            emailResponse.setStatusCode(response.getStatusCode());
            emailResponse.setMessage("Email Sent");
        } catch (IOException ex) {
           emailResponse.setStatusCode(0);
            StringWriter stringWriter = new StringWriter();
            ex.printStackTrace(new PrintWriter(stringWriter));
            emailResponse.setMessage("Exception while sending email."+stringWriter);
        }

        return emailResponse;
    }
}
