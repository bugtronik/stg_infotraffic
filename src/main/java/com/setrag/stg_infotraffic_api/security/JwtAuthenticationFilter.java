package com.setrag.stg_infotraffic_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder; // Gère le contexte de sécurité
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; // Pour charger les détails de l'utilisateur
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils; // Pour les opérations sur les chaînes
import org.springframework.web.filter.OncePerRequestFilter; // Pour s'assurer que le filtre est exécuté une seule fois par requête

import java.io.IOException;

/**
 * Filtre personnalisé qui intercepte chaque requête pour valider le JWT présent
 * dans l'en-tête "Authorization".
 */
@Component // Rend cette classe un composant Spring
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService; // Service pour charger les infos de l'utilisateur

    // Injection des dépendances via le constructeur
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 1. Tente d'extraire le JWT de l'en-tête "Authorization"
        String token = getTokenFromRequest(request);

        // 2. Si un token est trouvé et qu'il est valide
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
            // 3. Extrait le nom d'utilisateur du token
            String username = jwtTokenProvider.getUsername(token);

            // 4. Charge les détails de l'utilisateur (incluant les rôles/autorités)
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 5. Crée un objet d'authentification pour Spring Security
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, // Les détails de l'utilisateur
                    null,        // Le mot de passe n'est pas stocké dans le token JWT
                    userDetails.getAuthorities() // Les autorités/rôles de l'utilisateur
            );
            // Définit des détails supplémentaires pour l'authentification (comme l'adresse IP du client)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 6. Définit l'objet d'authentification dans le SecurityContext de Spring.
            // Cela indique à Spring Security que l'utilisateur est maintenant authentifié pour cette requête.
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Passe la requête au filtre suivant dans la chaîne de filtres Spring Security
        filterChain.doFilter(request, response);
    }

    /**
     * Méthode utilitaire pour extraire le JWT de l'en-tête "Authorization"
     * (format attendu : "Bearer <token>").
     * @param request La requête HTTP.
     * @return Le JWT sans le préfixe "Bearer ", ou null si non trouvé/malformé.
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        // Vérifie si l'en-tête est non vide et commence par "Bearer "
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Retourne la partie du token après "Bearer "
        }
        return null;
    }
}