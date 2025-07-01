package com.setrag.stg_infotraffic_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.dto.TrainPlannedDTO;
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

    private TrainPlannedDTO convertToDto(TrainPlanned train) {
        return new TrainPlannedDTO(
            train.getId(),
            train.getTrainNumber(),
            train.getTrainType(),
            train.getDepartureDate(),
            train.getDepartureTime(),
            train.getDepartureStation(),
            train.getArrivalStation(),
            train.getClasse(),
            train.getSeatsAvaibles()
        );
    }

    private TrainPlanned convertToEntity(TrainPlannedDTO trainDto) {
        TrainPlanned train = new TrainPlanned();
        train.setId(trainDto.getId()); // Pour les mises à jour
        train.setTrainNumber(trainDto.getTrainNumber());
        train.setTrainType(trainDto.getTrainType());
        train.setDepartureDate(trainDto.getDepartureDate());
        train.setDepartureTime(trainDto.getDepartureTime());
        train.setDepartureStation(trainDto.getDepartureStation());
        train.setArrivalStation(trainDto.getArrivalStation());
        train.setClasse(trainDto.getClasse());
        train.setSeatsAvaibles(trainDto.getSeatsAvaibles());
        return train;
    }

    public List<TrainPlannedDTO> getAllTrainsPlanned() {
        return trainPlannedRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TrainPlannedDTO getTrainPlannedById(Long id) {
        TrainPlanned train = trainPlannedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train planned with ID " + id + " not found"));
        return convertToDto(train);
    }

    public TrainPlannedDTO createTrainPlanned(TrainPlannedDTO trainDto) {
        TrainPlanned train = convertToEntity(trainDto);
        TrainPlanned savedTrain = trainPlannedRepository.save(train);
        return  convertToDto(savedTrain);
    }

    public TrainPlannedDTO updateTrainPlanned(Long id, TrainPlannedDTO trainDetailsDto) {
        TrainPlanned existingTrain = trainPlannedRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Train with ID " + id + " not found"));
        existingTrain.setTrainNumber(trainDetailsDto.getTrainNumber());
        existingTrain.setTrainType(trainDetailsDto.getTrainType());
        existingTrain.setDepartureDate(trainDetailsDto.getDepartureDate());
        existingTrain.setDepartureTime(trainDetailsDto.getDepartureTime());
        existingTrain.setDepartureStation(trainDetailsDto.getDepartureStation());
        existingTrain.setArrivalStation(trainDetailsDto.getArrivalStation());
        existingTrain.setClasse(trainDetailsDto.getClasse());
        existingTrain.setSeatsAvaibles(trainDetailsDto.getSeatsAvaibles());
        TrainPlanned updatedTrain = trainPlannedRepository.save(existingTrain);

        return convertToDto(updatedTrain);
    }

    public void deleteTrainPlanned(Long id) {
        trainPlannedRepository.deleteById(id);
    }
}
