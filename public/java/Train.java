package com.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trainNumber;
    private String source;
    private String destination;
    private int availableSeats;

    public Train() {} //const

    public Train(String trainNumber, String source, String destination, int availableSeats) {
        this.trainNumber = trainNumber;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }
    //getters
    public Long getId() {
        return id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    //setter
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}

