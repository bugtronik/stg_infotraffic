package com.setrag.stg_infotraffic_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.setrag.stg_infotraffic_api.model.TrainRoute;

@Repository
public interface TrainRouteRepository extends JpaRepository<TrainRoute, Long> {
    
}
