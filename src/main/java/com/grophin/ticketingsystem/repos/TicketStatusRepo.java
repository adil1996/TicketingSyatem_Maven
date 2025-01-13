package com.grophin.ticketingsystem.repos;

import com.grophin.ticketingsystem.models.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketStatusRepo extends JpaRepository<TicketStatus,String> {
    List<TicketStatus> findByStatus(String status);
    TicketStatus findByTicketId(String ticketId);
}
