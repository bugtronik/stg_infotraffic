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

import com.setrag.stg_infotraffic_api.model.TrainSeatsAvailable;
import com.setrag.stg_infotraffic_api.service.TrainSeatsAvailableService;

@RestController
@RequestMapping("/api/v1/trains-seats-available") // Endpoint pour cette ressource
public class TrainSeatsAvailableController {
    
    private final TrainSeatsAvailableService trainSeatsAvailableService;

    @Autowired
    public TrainSeatsAvailableController(TrainSeatsAvailableService trainSeatsAvailableService) {
        this.trainSeatsAvailableService = trainSeatsAvailableService;
    }

    // GET /api/v1/trains-seats-avaible
    @GetMapping
    public ResponseEntity<List<TrainSeatsAvailable>> getAllTrainSeatsAvailable() {
        List<TrainSeatsAvailable> trains = trainSeatsAvailableService.getAllTrainSeatsAvailable();
        return ResponseEntity.ok(trains); // Renvoie un 200 OK avec la liste des trains 
    }

    // GET /api/v1/trains-seats-available/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TrainSeatsAvailable> getTrainSeatsAvailableById(@PathVariable Long id) {
        TrainSeatsAvailable train = trainSeatsAvailableService.getTrainSeatsAvailableById(id);
        return ResponseEntity.ok(train); // renvoie 200 OK avec le train trouvé
    }

    // POST /api/v1/trains-seats-available
    @PostMapping
    public ResponseEntity<TrainSeatsAvailable> createTrainSeatsAvailable(@RequestBody TrainSeatsAvailable train) {
        TrainSeatsAvailable createdTrain = trainSeatsAvailableService.createTrainSeatsAvailable(train);
        return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
    }

    // PUT /api/v1/trains-seats-available/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TrainSeatsAvailable> updateTrainSeatsAvailable(@PathVariable Long id, @RequestBody TrainSeatsAvailable trainDetails) {
        TrainSeatsAvailable updatedTrain = trainSeatsAvailableService.updateTrainSeatsAvailable(id, trainDetails);
        return ResponseEntity.ok(updatedTrain);
    }

    // DELETE /api/v1/trains-seats-available/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainSeatsAvailable(@PathVariable Long id) {
        trainSeatsAvailableService.deleteTrainSeatsAvailable(id);
        return ResponseEntity.noContent().build(); // Renvoie un 204 No Content pour une suppression réussie
    }
}
