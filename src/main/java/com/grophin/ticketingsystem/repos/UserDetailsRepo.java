package com.grophin.ticketingsystem.repos;


import com.grophin.ticketingsystem.models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<UserDetails,String> {

    UserDetails findByUsertEmail(String email);
}
