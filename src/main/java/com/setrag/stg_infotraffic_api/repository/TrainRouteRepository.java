package com.setrag.stg_infotraffic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.setrag.stg_infotraffic_api.model.TrainRoute;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {
    
    Page<TrainRoute> findByTrainNumberContainingIgnoreCase(String trainNumber, Pageable pageable);
    Page<TrainRoute> findByStationContainingIgnoreCase(String station, Pageable pageable);
    Page<TrainRoute> findByTrainNumberContainingIgnoreCaseAndStationIgnoreCase(String trainNumber, String station, Pageable pageable);
}
