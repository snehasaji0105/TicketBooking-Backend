package com.example.ticket.booking.repository;

import com.example.ticket.booking.model.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketBookingRepo extends JpaRepository<TicketBooking,Long> {
//    List<TicketBooking> findById(int userId);

//     edit(TicketBooking ticketBooking);
}
