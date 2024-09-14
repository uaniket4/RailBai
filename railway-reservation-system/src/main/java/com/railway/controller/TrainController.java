package com.railway.controller;

import com.railway.model.Train;
import com.railway.service.TrainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/between")
    public List<Train> getTrainsBetweenStations(@RequestParam String fromStationCode, @RequestParam String toStationCode) {
        return trainService.getTrainsBetweenStations(fromStationCode, toStationCode);
    }
}
