package com.grophin.ticketingsystem;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@SpringBootApplication
public class TicketingSystem {

    private static final Logger logger = LogManager.getLogger(TicketingSystem.class);

    public static void main(String [] args){
    try {
        logger.info("************************ Starting the Ticketing System Service ************************");
        SpringApplication.run(TicketingSystem.class,args);
        logger.info("************************ Started the Ticketing System Service ************************");
    }
    catch (Exception ex){
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        logger.info("Exception while starting the ticketing System service\n"+stringWriter);
    }
  }
}
