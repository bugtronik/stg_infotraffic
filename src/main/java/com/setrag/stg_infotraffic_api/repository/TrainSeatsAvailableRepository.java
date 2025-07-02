package com.setrag.stg_infotraffic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.setrag.stg_infotraffic_api.model.TrainSeatsAvailable;

@Repository
public interface TrainSeatsAvailableRepository extends JpaRepository<TrainSeatsAvailable, Long> {
    
    // Méthode pour filtrer par numéro de train avec pagination
    Page<TrainSeatsAvailable> findByTrainNumberContainingIgnoreCase(String trainNumber, Pageable pageable);

    // Méthode pour filtrer par gare de départ avec pagination
    Page<TrainSeatsAvailable> findByDepartureStationContainingIgnoreCase(String departureStation, Pageable pageable);

    //Méthode pour filtrer par numéro de train et gare de départ avec pagination
    Page<TrainSeatsAvailable> findByTrainNumberContainingIgnoreCaseAndDepartureStationContainingIgnoreCase(
        String trainNumber, String departureStation, Pageable pageable
    );
}
