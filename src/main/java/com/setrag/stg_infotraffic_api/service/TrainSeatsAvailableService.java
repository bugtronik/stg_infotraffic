package com.setrag.stg_infotraffic_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.dto.TrainSeatsAvailableDTO;
import com.setrag.stg_infotraffic_api.exception.ResourceNotFoundException;
import com.setrag.stg_infotraffic_api.model.TrainSeatsAvailable;
import com.setrag.stg_infotraffic_api.repository.TrainSeatsAvailableRepository;

@Service
public class TrainSeatsAvailableService {
    
    private final TrainSeatsAvailableRepository trainSeatsAvailableRepository;

    @Autowired
    public TrainSeatsAvailableService(TrainSeatsAvailableRepository trainSeatsAvailableRepository) {
        this.trainSeatsAvailableRepository = trainSeatsAvailableRepository;
    }

    // --------- Méthode de convertion DTO <-> Entité -----------
    private TrainSeatsAvailableDTO convertToDto(TrainSeatsAvailable train) {
        return new TrainSeatsAvailableDTO(
            train.getId(),
            train.getTrainNumber(),
            train.getTrainType(),
            train.getDepartureDate(),
            train.getDepartureTime(),
            train.getDepartureStation(),
            train.getArrivalStation(),
            train.getSeatsAvaibles()
        );
    }

    private TrainSeatsAvailable convertToEntity(TrainSeatsAvailableDTO trainDto) {
        TrainSeatsAvailable train = new TrainSeatsAvailable();
        // L'ID n'est pas défini lors de la création, mais peut être utilisé lors de la mise à jour
        train.setId(trainDto.getId());
        train.setTrainNumber(trainDto.getTrainNumber());
        train.setTrainType(trainDto.getTrainType());
        train.setDepartureDate(trainDto.getDepartureDate());
        train.setDepartureTime(trainDto.getDepartureTime());
        train.setDepartureStation(trainDto.getDepartureStation());
        train.setArrivalStation(trainDto.getArrivalStation());
        train.setSeatsAvaibles(trainDto.getSeatsAvaibles());
        return train;
    }

    /**
     * Récupère tous les trains avec sièges disponibles.
     * @return une liste de tous les objets TrainSeatsAvailable.
     */
    public List<TrainSeatsAvailableDTO> getAllTrainSeatsAvailable() {
        return trainSeatsAvailableRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un train par son ID
     * @param id l'ID du train
     * @return l'objet TrainSeatsAvailable correspondant
     * @throws ResourceNotFoundException si le train n'est pas trouvé
     */
    public TrainSeatsAvailableDTO getTrainSeatsAvailableById(Long id) {
        TrainSeatsAvailable train = trainSeatsAvailableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train with ID " + id + " not found"));
        return convertToDto(train);
    }

    /**
     * Crée un nouveau train avec sièges disponibles
     * @param train l'objet TrainSeatsAvailable à sauvegarder.
     * @return l'objet sauvegardé.
     */
    public TrainSeatsAvailableDTO createTrainSeatsAvailable(TrainSeatsAvailableDTO trainDto) {
        TrainSeatsAvailable train = convertToEntity(trainDto);
        TrainSeatsAvailable savedTrain = trainSeatsAvailableRepository.save(train);
        return convertToDto(savedTrain);
    }

    /**
     * Met à jour un train existant.
     * @param id l'ID du train à mettre à jour.
     * @param trainDetailsDto les détails du train mis à jour.
     * @return l'objet TrainSeatsAvailable mis à jour.
     * @throws ResourceNotFoundException si le train n'est pas trouvé.
     */
    public TrainSeatsAvailableDTO updateTrainSeatsAvailable(Long id, TrainSeatsAvailableDTO trainDetailsDto) {
        TrainSeatsAvailable existingTrain = trainSeatsAvailableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train with ID " + id + " not found"));

        // Mettre à jour les champs de l'entité existante avec les détails du DTO
        existingTrain.setTrainNumber(trainDetailsDto.getTrainNumber());
        existingTrain.setTrainType(trainDetailsDto.getTrainType());
        existingTrain.setDepartureDate(trainDetailsDto.getDepartureDate());
        existingTrain.setDepartureTime(trainDetailsDto.getDepartureTime());
        existingTrain.setDepartureStation(trainDetailsDto.getDepartureStation());
        existingTrain.setArrivalStation(trainDetailsDto.getArrivalStation());
        existingTrain.setSeatsAvaibles(trainDetailsDto.getSeatsAvaibles());

        TrainSeatsAvailable updatedTrain = trainSeatsAvailableRepository.save(existingTrain);
        return convertToDto(updatedTrain);
    }

    /**
     * Supprime un train par son ID.
     * @param id l'ID du train à supprimer.
     */
    public void deleteTrainSeatsAvailable(Long id) {
        trainSeatsAvailableRepository.deleteById(id);
    }
}
