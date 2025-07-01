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

import com.setrag.stg_infotraffic_api.dto.TrainRouteDTO;
import com.setrag.stg_infotraffic_api.service.TrainRouteService;

@RestController
@RequestMapping("/api/v1/trains-routes")
public class TrainRouteController {
    private final TrainRouteService trainRouteService;

    @Autowired
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @GetMapping
    public ResponseEntity<List<TrainRouteDTO>> getAllTrainRoutes() {
        List<TrainRouteDTO> routes = trainRouteService.getAllTrainRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainRouteDTO> getTrainRouteById(@PathVariable Long id) {
        TrainRouteDTO route = trainRouteService.getTrainRouteById(id);
        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<TrainRouteDTO> createTrainRoute(@RequestBody TrainRouteDTO routeDto) {
        TrainRouteDTO createdRoute = trainRouteService.createTrainRoute(routeDto);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainRouteDTO> updateTrainRoute(@PathVariable Long id, @RequestBody TrainRouteDTO routeDetailsDto) {
        TrainRouteDTO updatedRoute = trainRouteService.updateTrainRoute(id, routeDetailsDto);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainRoute(@PathVariable Long id) {
        trainRouteService.deleteTrainRoute(id);
        return ResponseEntity.noContent().build();
    }
}
