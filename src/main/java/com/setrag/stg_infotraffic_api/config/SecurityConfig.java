package com.setrag.stg_infotraffic_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; // Important pour @PreAuthorize
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy; // Pour configurer la gestion de session
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Pour hacher les mots de passe
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // La chaîne de filtres de sécurité
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Un filtre par défaut de Spring Security

import com.setrag.stg_infotraffic_api.security.JwtAuthenticationEntryPoint;
import com.setrag.stg_infotraffic_api.security.JwtAuthenticationFilter;

/**
 * Classe de configuration principale pour Spring Security.
 */
@Configuration // Indique que c'est une classe de configuration Spring
@EnableMethodSecurity // Active la sécurité basée sur les annotations de méthode (@PreAuthorize, @PostAuthorize)
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Injection des deux composants JWT que nous avons créés
    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Définit un bean pour le hachage des mots de passe. BCrypt est fortement recommandé.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Définit un bean pour le gestionnaire d'authentification, qui est nécessaire pour l'authentification des utilisateurs.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Configure la chaîne de filtres de sécurité HTTP. C'est ici que nous définissons les règles d'accès.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Désactive la protection CSRF (utile pour les APIs REST stateless avec JWT)
            .csrf(AbstractHttpConfigurer::disable)
            // Configure la gestion des exceptions d'authentification (quand l'accès est refusé)
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            // Définit la politique de gestion de session comme stateless (pas de session côté serveur avec JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Définit les règles d'autorisation pour les requêtes HTTP
            .authorizeHttpRequests(authorize -> authorize
                // Autorise l'accès sans authentification à l'endpoint de connexion et à Swagger UI
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v1/api-docs/**").permitAll()
                // Toutes les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
            );

        // Ajoute notre filtre JWT personnalisé dans la chaîne de filtres de Spring Security,
        // avant le filtre d'authentification par défaut de Spring.
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}