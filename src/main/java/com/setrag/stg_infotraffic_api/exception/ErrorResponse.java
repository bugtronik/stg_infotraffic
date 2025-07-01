package com.setrag.stg_infotraffic_api.exception;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// Cette classe représente la structure des réponses d'erreur de notre GlobalExceptionHandler
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private Long timestamp;
    private List<String> messages; // Pour les erreurs de validation
}
