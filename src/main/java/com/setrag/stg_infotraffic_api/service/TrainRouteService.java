package com.setrag.stg_infotraffic_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.dto.TrainRouteDTO;
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

    private TrainRouteDTO convertToDto(TrainRoute route) {
        return new TrainRouteDTO(
            route.getId(),
            route.getTrainNumber(),
            route.getStation(),
            route.getTrainType(),
            route.getDepartureDate(),
            route.getArrivalTime(),
            route.getDepartureTime(),
            route.getStopTime()
        );
    }

    private TrainRoute convertToEntity(TrainRouteDTO routeDto) {
        TrainRoute route = new TrainRoute();
        route.setId(routeDto.getId()); // Pour les mises à jour
        route.setTrainNumber(routeDto.getTrainNumber());
        route.setStation(routeDto.getStation());
        route.setTrainType(routeDto.getTrainType());
        route.setDepartureDate(routeDto.getDepartureDate());
        route.setArrivalTime(routeDto.getArrivalTime());
        route.setDepartureTime(routeDto.getDepartureTime());
        route.setStopTime(routeDto.getStopTime());
        return route;
    }

    public List<TrainRouteDTO> getAllTrainRoutes() {
        return trainRouteRepository.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public TrainRouteDTO getTrainRouteById(Long id) {
        TrainRoute train = trainRouteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train route with ID " + id + " not found"));
        return convertToDto(train);
    }

    public TrainRouteDTO createTrainRoute(TrainRouteDTO routeDto) {
        TrainRoute route = convertToEntity(routeDto);
        TrainRoute savedRoute = trainRouteRepository.save(route);
        return convertToDto(savedRoute);
    }

    public TrainRouteDTO updateTrainRoute(Long id, TrainRouteDTO routeDetailsDto) {
        TrainRoute existingRoute = trainRouteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Train with ID " + id + " not found."));
        existingRoute.setTrainNumber(routeDetailsDto.getTrainNumber());
        existingRoute.setStation(routeDetailsDto.getStation());
        existingRoute.setTrainType(routeDetailsDto.getTrainType());
        existingRoute.setDepartureDate(routeDetailsDto.getDepartureDate());
        existingRoute.setArrivalTime(routeDetailsDto.getArrivalTime());
        existingRoute.setDepartureTime(routeDetailsDto.getDepartureTime());
        existingRoute.setStopTime(routeDetailsDto.getStopTime());
        TrainRoute updatedRoute = trainRouteRepository.save(existingRoute);

        return convertToDto(updatedRoute);
    }

    public void deleteTrainRoute(Long id) {
        trainRouteRepository.deleteById(id);
    }
}
