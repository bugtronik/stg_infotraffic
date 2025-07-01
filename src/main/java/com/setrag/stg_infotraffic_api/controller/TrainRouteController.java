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

import com.setrag.stg_infotraffic_api.model.TrainRoute;
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
    public ResponseEntity<List<TrainRoute>> getAllTrainRoutes() {
        List<TrainRoute> routes = trainRouteService.getAllTrainRoutes();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainRoute> getTrainRouteById(@PathVariable Long id) {
        TrainRoute route = trainRouteService.getTrainRouteById(id);
        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<TrainRoute> createTrainRoute(@RequestBody TrainRoute route) {
        TrainRoute createdRoute = trainRouteService.createTrainRoute(route);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainRoute> updateTrainRoute(@PathVariable Long id, @RequestBody TrainRoute routeDetails) {
        TrainRoute updatedRoute = trainRouteService.updateTrainRoute(id, routeDetails);
        return ResponseEntity.ok(updatedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainRoute(@PathVariable Long id) {
        trainRouteService.deleteTrainRoute(id);
        return ResponseEntity.noContent().build();
    }
}
