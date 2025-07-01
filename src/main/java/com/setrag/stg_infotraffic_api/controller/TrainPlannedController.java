package com.setrag.stg_infotraffic_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.stg_infotraffic_api.model.TrainPlanned;
import com.setrag.stg_infotraffic_api.service.TrainPlannedService;

@RestController
@RequestMapping("/api/v1/trains-planned")
public class TrainPlannedController {
    private final TrainPlannedService trainPlannedService;

    @Autowired
    public TrainPlannedController(TrainPlannedService trainPlannedService) {
        this.trainPlannedService = trainPlannedService;
    }

    @GetMapping
    public ResponseEntity<List<TrainPlanned>> getAllTrainsPlanned() {
        List<TrainPlanned> trains = trainPlannedService.getAllTrainsPlanned();
        return ResponseEntity.ok(trains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainPlanned> getTrainPlannedById(@PathVariable Long id) {
        TrainPlanned train = trainPlannedService.getTrainPlannedById(id);
        return ResponseEntity.ok(train);
    }

    @PostMapping
    public ResponseEntity<TrainPlanned> createTrainPlanned(@RequestBody TrainPlanned train) {
        TrainPlanned createdTrain = trainPlannedService.createTrainPlanned(train);
        return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainPlanned> updateTrainPlanned(@PathVariable Long id, @RequestBody TrainPlanned trainDetails) {
        TrainPlanned updatedTrain = trainPlannedService.updateTrainPlanned(id, trainDetails);
        return ResponseEntity.ok(updatedTrain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainPlanned(@PathVariable Long id) {
        trainPlannedService.deleteTrainPlanned(id);
        return ResponseEntity.noContent().build();
    }
}
