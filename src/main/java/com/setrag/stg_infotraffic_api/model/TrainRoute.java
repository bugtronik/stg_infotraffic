package com.setrag.stg_infotraffic_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trains_routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainRoute {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String station;
    private String trainType;
    private String departureDate;
    private Long arrivalTime;
    private Long departureTime;
    private Long stopTime;
}
