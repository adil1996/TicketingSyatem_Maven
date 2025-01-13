package com.grophin.ticketingsystem.repos;

import com.grophin.ticketingsystem.models.AssignedAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignedAgentRepo extends JpaRepository<AssignedAgent,String> {
    AssignedAgent findByTicketId(String ticketId);
    List<AssignedAgent> findByAgentId(String agentId);
}
