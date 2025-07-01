package com.setrag.stg_infotraffic_api.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Gère les exceptions de type ResourceNotFoundException
     * Renvoie un statut HTTP 404 (Not Found)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère les exceptions de validation (@Valid) levées par les DTOs
     * Renvoir un statut HTTP 400 (Bad Request) avec les détails des erreurs de validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("timestamp", System.currentTimeMillis());

        List<String> errors = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

        body.put("message", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Gère toutes les autres exceptions non spécifiquement traitées
     * Renvoie un statut HTTP 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "An unexpected error occured: " + ex.getMessage());
        // Pour la production, nous n'allons pas exposer ex.getMessage() pour des raisons de sécurité
        // Le code ci-dessus sera utilisé pour la production
        // body.put("message", "An unexpected error occurred. Please try again later.");
        body.put("timestamp", System.currentTimeMillis());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
