package com.grophin.ticketingsystem.repos;

import com.grophin.ticketingsystem.models.TicketResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketResposeRepo extends JpaRepository<TicketResponse,String> {
    TicketResponse findByTicketId(String ticketId);
}
