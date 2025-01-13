package com.grophin.ticketingsystem.models;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "User_Details")
@Data
public class UserDetails implements Serializable {

    @Id
    @Column(name="UserID")
    String userId;

    @Column(name="user_name")
    String userName;

    @Column(name="UserEmail")
    String usertEmail;

    @Column(name="UserContactNo")
    String userContactNo;

    @Column(name = "UserAddress")
    String userAddress;

    @Column(name = "Password")
    String password;

}
