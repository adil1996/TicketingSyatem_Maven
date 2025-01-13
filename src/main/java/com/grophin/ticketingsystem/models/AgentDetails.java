package com.grophin.ticketingsystem.models;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Agent_Details")
@Data
public class AgentDetails {

    @Id
    @Column(name="AgentID")
    String agentId;

    @Column(name="AgentName")
    String agentName;

    @Column(name="AgentEmail")
    String agentEmail;

    @Column(name="AgentContactNo")
    String agentContactNo;

    @Column(name = "AgentAddress")
    String address;

    @Column(name = "Password")
    String password;

}
