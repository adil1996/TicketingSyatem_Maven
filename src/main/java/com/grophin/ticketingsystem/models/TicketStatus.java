package com.grophin.ticketingsystem.models;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TicketStatus")
@Data
public class TicketStatus implements Serializable {

    @Id
    @Column(name = "IDTicket")
    String ticketId;

    @Column(name = "Status")
    String status;
}
