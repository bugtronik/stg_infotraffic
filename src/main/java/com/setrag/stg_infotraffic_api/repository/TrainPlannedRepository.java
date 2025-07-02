package com.setrag.stg_infotraffic_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.setrag.stg_infotraffic_api.model.TrainPlanned;

@Repository
public interface TrainPlannedRepository extends JpaRepository<TrainPlanned, Long> {
    Page<TrainPlanned> findByTrainNumberContainingIgnoreCase(String trainNumber, Pageable pageable);
    Page<TrainPlanned> findByClasse(String classe, Pageable pageable);
    Page<TrainPlanned> findByTrainTypeContainingIgnoreCaseAndClasse(String trainType, String classe, Pageable pageable);
}
