package com.setrag.stg_infotraffic_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Récupère tous les trains avec sièges disponibles.
     * @return une liste de tous les objets TrainSeatsAvailable.
     */
    public List<TrainSeatsAvailable> getAllTrainSeatsAvailable() {
        return trainSeatsAvailableRepository.findAll();
    }

    /**
     * Récupère un train par son ID
     * @param id l'ID du train
     * @return l'objet TrainSeatsAvailable correspondant
     * @throws ResourceNotFoundException si le train n'est pas trouvé
     */
    public TrainSeatsAvailable getTrainSeatsAvailableById(Long id) {
        return trainSeatsAvailableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Train with ID " + id + " not found"));
    }

    /**
     * Crée un nouveau train avec sièges disponibles
     * @param train l'objet TrainSeatsAvailable à sauvegarder.
     * @return l'objet sauvegardé.
     */
    public TrainSeatsAvailable createTrainSeatsAvailable(TrainSeatsAvailable train) {
        return trainSeatsAvailableRepository.save(train);
    }

    /**
     * Met à jour un train existant.
     * @param id l'ID du train à mettre à jour.
     * @param trainDetails les détails du train mis à jour.
     * @return l'objet TrainSeatsAvailable mis à jour.
     * @throws ResourceNotFoundException si le train n'est pas trouvé.
     */
    public TrainSeatsAvailable updateTrainSeatsAvailable(Long id, TrainSeatsAvailable trainDetails) {
        TrainSeatsAvailable train = getTrainSeatsAvailableById(id);
        train.setTrainNumber(trainDetails.getTrainNumber());
        train.setTrainType(trainDetails.getTrainType());
        train.setDepartureDate(trainDetails.getDepartureDate());
        train.setDepartureTime(trainDetails.getDepartureTime());
        train.setDepartureStation(trainDetails.getDepartureStation());
        train.setArrivalStation(trainDetails.getArrivalStation());
        train.setSeatsAvaibles(trainDetails.getSeatsAvaibles());
        return trainSeatsAvailableRepository.save(train);
    }

    /**
     * Supprime un train par son ID.
     * @param id l'ID du train à supprimer.
     */
    public void deleteTrainSeatsAvailable(Long id) {
        trainSeatsAvailableRepository.deleteById(id);
    }
}
