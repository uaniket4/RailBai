package com.railway.model;

import lombok.Data;

@Data
public class Reservation {
    private Long id; // Unique identifier for the reservation
    private String trainNumber;
    private String passengerName;
    private String fromStation;
    private String toStation;
    private String reservationDate;
    private int seatNumber;
    private String status; // e.g., Confirmed, Pending, Cancelled
}
