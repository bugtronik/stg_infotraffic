package com.setrag.stg_infotraffic_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.exception.ResourceNotFoundException;
import com.setrag.stg_infotraffic_api.model.TrainRoute;
import com.setrag.stg_infotraffic_api.repository.TrainRouteRepository;

@Service
public class TrainRouteService {
    private final TrainRouteRepository trainRouteRepository;

    @Autowired
    public TrainRouteService(TrainRouteRepository trainRouteRepository) {
        this.trainRouteRepository = trainRouteRepository;
    }

    public List<TrainRoute> getAllTrainRoutes() {
        return trainRouteRepository.findAll();
    }

    public TrainRoute getTrainRouteById(Long id) {
        return trainRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train route with ID " + id + " not found"));
    }

    public TrainRoute createTrainRoute(TrainRoute route) {
        return trainRouteRepository.save(route);
    }

    public TrainRoute updateTrainRoute(Long id, TrainRoute routeDetails) {
        TrainRoute route = getTrainRouteById(id);
        route.setTrainNumber(routeDetails.getTrainNumber());
        route.setStation(routeDetails.getStation());
        route.setTrainType(routeDetails.getTrainType());
        route.setDepartureDate(routeDetails.getDepartureDate());
        route.setArrivalTime(routeDetails.getArrivalTime());
        route.setDepartureTime(routeDetails.getDepartureTime());
        route.setStopTime(routeDetails.getStopTime());
        return trainRouteRepository.save(route);
    }

    public void deleteTrainRoute(Long id) {
        trainRouteRepository.deleteById(id);
    }
}
