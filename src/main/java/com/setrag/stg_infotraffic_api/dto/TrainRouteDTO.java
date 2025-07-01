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
public class TrainRouteDTO {
    private Long id;

    @NotBlank(message = "Le numéro de train est obligatoire")
    private String trainNumber;

    @NotBlank(message = "La gare est obligatoire")
    private String station;

    @NotBlank(message = "Le type de train est obligatoire")
    private String trainType;

    @NotBlank(message = "La date de départ est obligatoire")
    private String departureDate;

    @NotNull(message = "L'heure d'arrivée est obligatoire")
    @Min(value = 0, message = "L'heure d'arrivée doit être un nombre positif")
    private Long arrivalTime;

    @NotNull(message = "L'heure de départ est obligatoire")
    @Min(value = 0, message = "L'heure de départ doit être un nombre positif")
    private Long departureTime;

    @NotNull(message = "Le temps d'arrêt est obligatoire")
    @Min(value = 0, message = "Le temps d'arrêt doit être un nombre positif")
    private Long stopTime;
}
