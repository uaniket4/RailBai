package com.railway.service;

import com.railway.model.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    private final RestTemplate restTemplate;

    public ReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Fetch all reservations (example API call)
    public List<Reservation> getAllReservations() {
        String apiUrl = "https://api.example.com/reservations";
        Reservation[] response = restTemplate.getForObject(apiUrl, Reservation[].class);
        return Arrays.asList(response);
    }

    // Create a new reservation
    public Reservation createReservation(Reservation reservation) {
        String apiUrl = "https://api.example.com/reservations";
        return restTemplate.postForObject(apiUrl, reservation, Reservation.class);
    }

    // Update an existing reservation
    public void updateReservation(Long id, Reservation reservation) {
        String apiUrl = "https://api.example.com/reservations/" + id;
        restTemplate.put(apiUrl, reservation);
    }

    // Cancel a reservation
    public void cancelReservation(Long id) {
        String apiUrl = "https://api.example.com/reservations/" + id;
        restTemplate.delete(apiUrl);
    }
}
