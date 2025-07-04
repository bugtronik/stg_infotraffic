package com.setrag.stg_infotraffic_api.service;

import org.springframework.security.core.GrantedAuthority; // Représente une autorité/un rôle
import org.springframework.security.core.authority.SimpleGrantedAuthority; // Implémentation simple de GrantedAuthority
import org.springframework.security.core.userdetails.User; // La classe User de Spring Security
import org.springframework.security.core.userdetails.UserDetails; // L'interface pour les détails de l'utilisateur
import org.springframework.security.core.userdetails.UserDetailsService; // L'interface du service
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Exception pour utilisateur non trouvé
import org.springframework.security.crypto.password.PasswordEncoder; // Pour hacher les mots de passe
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * Implémentation personnalisée de UserDetailsService.
 * Elle est responsable de charger les détails d'un utilisateur à partir de sa chaîne de caractères de nom d'utilisateur.
 * Actuellement, elle simule des utilisateurs en mémoire à des fins de démonstration.
 * En production, ces utilisateurs proviendraient d'une base de données.
 */
@Service // Rend cette classe un service Spring
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder; // Pour hacher les mots de passe des utilisateurs simulés

    // Injection du PasswordEncoder
    public CustomUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // *** IMPORTANT : Ceci est une implémentation simplifiée pour l'exemple. ***
        // En production, vous feriez une requête à votre base de données (via un repository)
        // pour trouver l'utilisateur par son nom d'utilisateur et charger ses rôles associés.

        if ("admin".equals(username)) {
            // Simule un utilisateur "admin" avec le rôle "ROLE_ADMIN"
            Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
            // Crée un objet UserDetails de Spring Security avec le nom, le mot de passe haché, et les autorités
            return new User(username, passwordEncoder.encode("password"), authorities); // "password" sera haché
        } else if ("user".equals(username)) {
            // Simule un utilisateur "user" avec le rôle "ROLE_USER"
            Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(username, passwordEncoder.encode("userpass"), authorities); // "userpass" sera haché
        }
        // Si aucun utilisateur ne correspond, lance une exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}