package com.setrag.stg_infotraffic_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trains_planned")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainPlanned {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;
    private String trainType;
    private String departureDate;
    private Long departureTime;
    private String departureStation;
    private String arrivalStation;
    private String classe;
    private Integer seatsAvaibles;
}
