package com.setrag.stg_infotraffic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.setrag.stg_infotraffic_api.model.TrainPlanned;

@Repository
public interface TrainPlannedRepository extends JpaRepository<TrainPlanned, Long> {
    
}
