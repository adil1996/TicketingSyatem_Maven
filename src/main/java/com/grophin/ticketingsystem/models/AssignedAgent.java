package com.grophin.ticketingsystem.models;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "AssignedAgent")
@Data
public class AssignedAgent {

    @Id
    @Column(name="TicketID")
    String ticketId;

    @Column(name="AgentID")
    String agentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "AgentID", referencedColumnName = "AgentID", insertable = false, updatable = false)
    })
    private AgentDetails agentDetails;

}
