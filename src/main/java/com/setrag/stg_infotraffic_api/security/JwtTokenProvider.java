package com.setrag.stg_infotraffic_api.security;

import io.jsonwebtoken.*; // Importe toutes les classes de base de JJWT
import io.jsonwebtoken.security.Keys; // Pour créer des objets Key sécurisés
import org.springframework.beans.factory.annotation.Value; // Pour injecter les valeurs de application.properties
import org.springframework.security.core.Authentication; // Objet d'authentification de Spring Security
import org.springframework.stereotype.Component;
import java.util.Date;

import javax.crypto.SecretKey;

/**
 * Fournit les fonctionnalités pour générer, valider et extraire des informations des JWT.
 */
@Component // Rend cette classe un composant Spring géré par le conteneur IoC
public class JwtTokenProvider {

    // Injecte la clé secrète JWT depuis application.properties
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    // Injecte la durée d'expiration du JWT depuis application.properties
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // Méthode utilitaire pour obtenir l'objet Key à partir de la clé secrète Base64
     private SecretKey key() {
        String secret = "votre_secret_tres_long_et_securise";
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Génère un nouveau JWT pour un utilisateur authentifié.
     * @param authentication L'objet Authentication de Spring Security contenant le nom d'utilisateur.
     * @return La chaîne de caractères représentant le JWT généré.
     */
    public String generateToken(Authentication authentication) {
        String username = authentication.getName(); // Récupère le nom d'utilisateur

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        // Construction du JWT en utilisant la nouvelle API du builder de JJWT (version 0.11.0+)
        String token = Jwts.builder()
                .subject(username) // Définit le sujet (généralement le nom d'utilisateur)
                .issuedAt(currentDate) // Définit la date de création du token
                .expiration(expireDate) // Définit la date d'expiration du token
                .signWith(key()) // Signe le token avec notre clé secrète
                .compact(); // Finalise la construction et compacte le token en chaîne
        return token;
    }

    /**
     * Récupère le nom d'utilisateur (subject) à partir d'un JWT donné.
     * @param token Le JWT à analyser.
     * @return Le nom d'utilisateur extrait du token.
     */
    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key()) // <-- Utilisation de verifyWith() au lieu de setSigningKey()
                .build()
                .parseSignedClaims(token) // <-- Nouvelle méthode
                .getPayload()
                .getSubject();
    }

    /**
     * Valide la conformité et l'authenticité d'un JWT.
     * @param token Le JWT à valider.
     * @return true si le token est valide. Lance une RuntimeException si le token est invalide.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(token); // <-- Parse simple pour validation
            return true;
        } catch (Exception e) {
            throw new RuntimeException("JWT invalide: " + e.getMessage(), e);
        }
    }
}