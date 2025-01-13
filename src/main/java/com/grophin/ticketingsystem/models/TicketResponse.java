package com.grophin.ticketingsystem.models;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name="TicketResponse")
public class TicketResponse {

    @Id
    @Column(name = "TicketId")
    String ticketId;

    @Column(name = "Response")
    String response;
}
