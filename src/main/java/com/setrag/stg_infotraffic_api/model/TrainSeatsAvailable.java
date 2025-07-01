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
@Table(name = "trains_seats_avaibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainSeatsAvailable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String trainType;
    private String departureDate;
    private Long departureTime; // Correspond au type bigint
    private String departureStation;
    private String arrivalStation;
    private Integer seatsAvaibles; // Correspond au type int
}
