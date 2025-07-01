package com.setrag.stg_infotraffic_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.exception.ResourceNotFoundException;
import com.setrag.stg_infotraffic_api.model.TrainPlanned;
import com.setrag.stg_infotraffic_api.repository.TrainPlannedRepository;

@Service
public class TrainPlannedService {
    private final TrainPlannedRepository trainPlannedRepository;

    @Autowired
    public TrainPlannedService(TrainPlannedRepository trainPlannedRepository) {
        this.trainPlannedRepository = trainPlannedRepository;
    }

    public List<TrainPlanned> getAllTrainsPlanned() {
        return trainPlannedRepository.findAll();
    }

    public TrainPlanned getTrainPlannedById(Long id) {
        return trainPlannedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train planned with ID " + id + " not found"));
    }

    public TrainPlanned createTrainPlanned(TrainPlanned train) {
        return trainPlannedRepository.save(train);
    }

    public TrainPlanned updateTrainPlanned(Long id, TrainPlanned trainDetails) {
        TrainPlanned train = getTrainPlannedById(id);
        train.setTrainNumber(trainDetails.getTrainNumber());
        train.setTrainType(trainDetails.getTrainType());
        train.setDepartureDate(trainDetails.getDepartureDate());
        train.setDepartureTime(trainDetails.getDepartureTime());
        train.setDepartureStation(trainDetails.getDepartureStation());
        train.setArrivalStation(trainDetails.getArrivalStation());
        train.setClasse(trainDetails.getClasse());
        train.setSeatsAvaibles(trainDetails.getSeatsAvaibles());
        return trainPlannedRepository.save(train);
    }

    public void deleteTrainPlanned(Long id) {
        trainPlannedRepository.deleteById(id);
    }
}
