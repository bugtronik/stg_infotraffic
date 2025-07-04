package com.setrag.stg_infotraffic_api.dto;

import lombok.AllArgsConstructor; // Pour générer un constructeur avec tous les arguments
import lombok.Data;
import lombok.NoArgsConstructor; // Pour générer un constructeur sans argument

@Data // Génère getters, setters, toString, etc.
@NoArgsConstructor // Génère un constructeur par défaut
@AllArgsConstructor // Génère un constructeur avec tous les champs
public class JwtAuthResponseDto {
    private String accessToken; // Le JWT lui-même
    private String tokenType = "Bearer"; // Type de token standard, toujours "Bearer" pour les JWT HTTP
}