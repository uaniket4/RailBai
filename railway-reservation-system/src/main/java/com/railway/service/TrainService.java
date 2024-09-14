package com.railway.service;

import com.railway.model.Train;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TrainService {

    private final RestTemplate restTemplate;

    public TrainService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Train> getTrainsBetweenStations(String fromStationCode, String toStationCode) {
        // API URL
        String apiUrl = "https://irctc1.p.rapidapi.com/api/v3/trainBetweenStations?fromStationCode=" + fromStationCode + "&toStationCode=" + toStationCode;

        // API Response
        Train[] response = restTemplate.getForObject(apiUrl, Train[].class);

        // Convert array to List and return
        return Arrays.asList(response);
    }
}
