package com.setrag.stg_infotraffic_api.dto;

import lombok.Data; // Pour générer automatiquement getters/setters/toString
import jakarta.validation.constraints.NotBlank; // Pour la validation

@Data // Annotation Lombok pour réduire le code boilerplate
public class LoginDto {
    @NotBlank(message = "Le nom d'utilisateur est obligatoire") // Assure que le champ n'est pas vide
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}
