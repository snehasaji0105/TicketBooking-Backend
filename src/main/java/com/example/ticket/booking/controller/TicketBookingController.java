package com.example.ticket.booking.controller;

import com.example.ticket.booking.model.TicketBooking;
import com.example.ticket.booking.model.User;
import com.example.ticket.booking.repository.TicketBookingRepo;
import com.example.ticket.booking.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping("/api")

public class TicketBookingController {
    @Autowired
    private TicketBookingService ticketBookingService;

    @GetMapping("get-tickets")
    public List<TicketBooking>getAllTickets(){
        return ticketBookingService.getAllTickets();
    }

    @PostMapping("add-tickets")
    public String addTicket(@RequestBody TicketBooking ticketBooking){

        return ticketBookingService.addTicket(ticketBooking);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("cancel-tickets/{id}")
    public ResponseEntity<String> cancelTicket(@PathVariable Long id){
        ticketBookingService.cancelTicket(id);
        return  ResponseEntity.ok("ok") ;
    }

//    @PutMapping("edit-tickets/{id}")
//    public ResponseEntity<String> editTicket(@PathVariable Long id){
//        ticketBookingService.editTicket(id);
//        return  ResponseEntity.ok("ok") ;
//    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//@PutMapping("update-ticket/{id}")
//    public ResponseEntity<String>updateTicket(@PathVariable long id,@RequestBody TicketBooking ticketBooking){
//        return ticketBookingService.updateTicket(id,ticketBooking);
//}

    @GetMapping("get-users")
    public List<User>getAllUsers(@PathVariable int userId){
        return ticketBookingService.getAllUsers();
    }

}
