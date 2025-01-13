package com.grophin.ticketingsystem.repos;

import com.grophin.ticketingsystem.models.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketDetailsRepo extends JpaRepository<TicketDetails,String> {
    TicketDetails findByTicketId(String ticketid);
    List<TicketDetails>findByCustomer(String customer);
}
