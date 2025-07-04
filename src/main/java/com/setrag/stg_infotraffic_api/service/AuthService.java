package com.setrag.stg_infotraffic_api.service;

import org.springframework.security.authentication.AuthenticationManager; // Gère le processus d'authentification
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Représente les identifiants utilisateur
import org.springframework.security.core.Authentication; // L'objet d'authentification résultant
import org.springframework.security.core.context.SecurityContextHolder; // Contexte de sécurité de Spring
import org.springframework.stereotype.Service;

import com.setrag.stg_infotraffic_api.dto.LoginDto;
import com.setrag.stg_infotraffic_api.security.JwtTokenProvider;

/**
 * Service métier pour gérer les opérations d'authentification.
 */
@Service // Indique que c'est un service Spring
public class AuthService {

    private final AuthenticationManager authenticationManager; // Gérant d'authentification injecté par Spring Security
    private final JwtTokenProvider jwtTokenProvider; // Notre utilitaire JWT

    // Injection des dépendances
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Tente d'authentifier un utilisateur et, si réussi, génère un JWT.
     * @param loginDto Les identifiants de connexion de l'utilisateur.
     * @return Le JWT généré si l'authentification est réussie.
     */
    public String login(LoginDto loginDto) {
        // Utilise l'AuthenticationManager pour tenter d'authentifier l'utilisateur
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        // Si l'authentification est réussie, l'objet Authentication est stocké dans le SecurityContext.
        // Cela signifie que l'utilisateur est maintenant considéré comme authentifié pour la durée de cette requête.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Génère un JWT pour l'utilisateur fraîchement authentifié
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }
}