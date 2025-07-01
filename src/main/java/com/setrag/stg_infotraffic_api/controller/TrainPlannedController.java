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

import com.setrag.stg_infotraffic_api.dto.TrainPlannedDTO;
import com.setrag.stg_infotraffic_api.service.TrainPlannedService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trains-planned")
public class TrainPlannedController {
    private final TrainPlannedService trainPlannedService;

    @Autowired
    public TrainPlannedController(TrainPlannedService trainPlannedService) {
        this.trainPlannedService = trainPlannedService;
    }

    @GetMapping
    public ResponseEntity<List<TrainPlannedDTO>> getAllTrainsPlanned() {
        List<TrainPlannedDTO> trains = trainPlannedService.getAllTrainsPlanned();
        return ResponseEntity.ok(trains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainPlannedDTO> getTrainPlannedById(@PathVariable Long id) {
        TrainPlannedDTO train = trainPlannedService.getTrainPlannedById(id);
        return ResponseEntity.ok(train);
    }

    @PostMapping
    public ResponseEntity<TrainPlannedDTO> createTrainPlanned(@Valid @RequestBody TrainPlannedDTO trainDto) {
        TrainPlannedDTO createdTrain = trainPlannedService.createTrainPlanned(trainDto);
        return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainPlannedDTO> updateTrainPlanned(@PathVariable Long id, @Valid @RequestBody TrainPlannedDTO trainDetailsDto) {
        TrainPlannedDTO updatedTrain = trainPlannedService.updateTrainPlanned(id, trainDetailsDto);
        return ResponseEntity.ok(updatedTrain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainPlanned(@PathVariable Long id) {
        trainPlannedService.deleteTrainPlanned(id);
        return ResponseEntity.noContent().build();
    }
}
