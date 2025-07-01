package com.setrag.stg_infotraffic_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainPlannedDTO {
    private Long id;

    @NotBlank(message = "Le numéro de train est obligatoire")
    private String trainNumber;

    @NotBlank(message = "Le type de train est obligatoire")
    private String trainType;

    @NotBlank(message = "La date de départ est obligatoire")
    private String departureDate;

    @NotNull(message = "L'heure de départ est obligatoire")
    @Min(value = 0, message = "L'heure de départ doit être un nombre positif")
    private Long departureTime;

    @NotBlank(message = "La gare de départ est obligatoire")
    private String departureStation;

    @NotBlank(message = "La gare d'arrivée est obligatoire")
    private String arrivalStation;

    @NotBlank(message = "La classe est obligatoire")
    private String classe;

    @NotNull(message = "Le nombre de sièges disponibles est obligatoire")
    @Min(value = 0, message = "Le nombre de sièges disponibles ne peut pas être négatif")
    private Integer seatsAvaibles;
}
