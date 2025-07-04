package com.setrag.stg_infotraffic_api.controller;

import jakarta.validation.Valid; // Pour la validation des DTOs
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.stg_infotraffic_api.dto.JwtAuthResponseDto;
import com.setrag.stg_infotraffic_api.dto.LoginDto;
import com.setrag.stg_infotraffic_api.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Indique que c'est un contrôleur REST
@RequestMapping("/api/v1/auth") // Définition du chemin de base pour ce contrôleur
@Tag(name = "Authentification", description = "Endpoints pour la gestion de l'authentification et des tokens JWT")
public class AuthController {

    private final AuthService authService; // Notre service d'authentification

    // Injection du service d'authentification
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint public pour la connexion des utilisateurs.
     * Prend les identifiants, tente l'authentification et retourne un JWT.
     * @param loginDto Les identifiants de connexion fournis par l'utilisateur.
     * @return Une ResponseEntity contenant le JWT et un statut HTTP 200 OK.
     */
    @Operation(summary = "Connexion utilisateur et obtention d'un token JWT",
               description = "Authentifie un utilisateur avec un nom d'utilisateur et un mot de passe, et retourne un token JWT en cas de succès.")
    @PostMapping("/login") // Mappe les requêtes POST à /api/v1/auth/login
    public ResponseEntity<JwtAuthResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto); // Appelle le service pour l'authentification et la génération du token

        // Construit l'objet de réponse JWT
        JwtAuthResponseDto jwtAuthResponse = new JwtAuthResponseDto();
        jwtAuthResponse.setAccessToken(token); // Définit le token généré
        jwtAuthResponse.setTokenType("Bearer"); // Assure que le type de token est bien "Bearer"

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK); // Retourne la réponse avec un statut OK
    }
}