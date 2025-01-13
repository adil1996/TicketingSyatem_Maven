package com.grophin.ticketingsystem.repos;

import com.grophin.ticketingsystem.models.AgentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentsDetailsRepo extends JpaRepository<AgentDetails,String> {

    AgentDetails findByAgentEmail(String email);
}
