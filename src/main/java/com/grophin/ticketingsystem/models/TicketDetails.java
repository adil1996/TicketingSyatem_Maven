package com.grophin.ticketingsystem.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Table(name = "Ticket_Details")
@Data
public class TicketDetails {

    @Id
    @Column(name="TicketID")
    private String ticketId;

    @Column(name="Type")
    String type;

    @Column(name="Description")
    String description;

    @Column(name="Title")
    String title;

    @Column(name = "create_user")
    String createUser;

    @Column(name = "Priority")
    int priority;

    @Column(name = "Customer")
    String customer;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Deadline")
    Date deadline;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "TicketID", referencedColumnName = "IDTicket", insertable = false, updatable = false)
    })
    private TicketStatus ticketStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "create_user", referencedColumnName = "user_name", insertable = false, updatable = false)
    })
    private UserDetails userDetails;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "TicketID", referencedColumnName = "TicketID", insertable = false, updatable = false)
    })
    private AssignedAgent assignedAgent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "TicketID", referencedColumnName = "TicketId", insertable = false, updatable = false)
    })
    private TicketResponse ticketResponse;




}
