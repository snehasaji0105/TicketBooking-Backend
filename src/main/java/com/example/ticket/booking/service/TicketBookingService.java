package com.example.ticket.booking.service;

import com.example.ticket.booking.model.TicketBooking;
import com.example.ticket.booking.model.User;
import com.example.ticket.booking.repository.TicketBookingRepo;
import com.example.ticket.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketBookingService {
    @Autowired
    private TicketBookingRepo ticketBookingRepo;

    @Autowired
    private UserRepository userRepo;

    public List<TicketBooking> getAllTickets(){
        return ticketBookingRepo.findAll();
    }

//    public List<TicketBooking> getTicketsByUserId(/*int userId*/){
//        return ticketBookingRepo.findAll();
//    }


    public String addTicket(TicketBooking ticketBooking){
        return ticketBookingRepo.save(ticketBooking).toString();
    }




    public String cancelTicket(Long id){
        TicketBooking ticketBooking=ticketBookingRepo.findById(id).orElseThrow();
        ticketBookingRepo.delete(ticketBooking);
        return "deleted successfully";
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


//    public ResponseEntity<String> updateTicket(long id, TicketBooking updateTicket) {
//        try {
//            TicketBooking ticket = ticketBookingRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("Employee->"+updateTicket.toString()+" with id:"+id+" not found"));
//            ticket.setBusNumber(updateTicket.getBusNumber());
//            ticket.setDestination(updateTicket.getDestination());
//            ticket.setSource(updateTicket.getSource());
//            ticket.setBookedBy(updateTicket.getBookedBy());
//            ticket.setBookingDate(updateTicket.getBookingDate());
//            ticket.setJourneyDate(updateTicket.getJourneyDate());
//            ticket.setNo_of_passengers(ticket.getNo_of_passengers());
//            ticketBookingRepo.save(ticket);
//            return new ResponseEntity<String>("updated successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }



}
