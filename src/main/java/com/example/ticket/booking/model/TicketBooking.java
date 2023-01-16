package com.example.ticket.booking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class TicketBooking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long busNumber;
    private String destination;
    private String source;
    private String bookedBy;
//    private int userId;
    private String journeyDate;
    private String bookingDate;
    private int no_of_passengers;
}
