package com.setrag.stg_infotraffic_api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.stg_infotraffic_api.dto.TrainSeatsAvailableDTO;
import com.setrag.stg_infotraffic_api.service.TrainSeatsAvailableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trains-seats-available")
@Tag(name = "Trains Seats Available", description = "API pour la gestion des sièges disponibles des trains du jour en cours")
public class TrainSeatsAvailableController {
    
    private final TrainSeatsAvailableService trainSeatsAvailableService;

    public TrainSeatsAvailableController(TrainSeatsAvailableService trainSeatsAvailableService) {
        this.trainSeatsAvailableService = trainSeatsAvailableService;
    }

    @Operation(summary = "Récupérer tous les trains avec sièges disponibles",
                description = "Permet de lister tous les trains du jour en cours et le nombre de sièges disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des trains récupérée avec succès",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TrainSeatsAvailableDTO.class)))
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Seuls les ADMINs et USERs peuvent lire
    public ResponseEntity<Page<TrainSeatsAvailableDTO>> getTrainSeatsAvailable(
        @Parameter(description = "Numéro de train à filtrer (recherche partielle insensible à la casse)")
        @RequestParam(required = false) String trainNumber,
        @Parameter(description = "Gare de départ à filtrer (recherche partielle insensible à la casse)")
        @RequestParam(required = false) String departureStation,
        @PageableDefault(page = 0, size = 10, sort = "id")
        @Parameter(description = "Informations de pagination (page, size, sort). ?page=0&size=10&sort=trainNumber,asc")
        Pageable pageable
    ) {
        Page<TrainSeatsAvailableDTO> trainsPage = trainSeatsAvailableService.getTrainSeatsAvailable(trainNumber, departureStation, pageable);
        return ResponseEntity.ok(trainsPage);
    }

    @Operation(summary = "Récupérer un train par ID",
                description = "Permet de récupérer les détails d'un train spécifique par son identifiant unique.")
    @ApiResponse(responseCode = "200", description = "Train récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Train non trouvé",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // Seuls les ADMINs et USERs peuvent lire
    public ResponseEntity<TrainSeatsAvailableDTO> getTrainSeatsAvailableById(@PathVariable Long id) {
        TrainSeatsAvailableDTO train = trainSeatsAvailableService.getTrainSeatsAvailableById(id);
        return ResponseEntity.ok(train); // renvoie 200 OK avec le train trouvé
    }

    @Operation(summary = "Créer un nouveau train avec sièges disponibles",
                description = "Ajoute un nouveau train avec ses informations de sièges disponibles à la base de données.")
    @ApiResponse(responseCode = "201", description = "Train créé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')") // Seuls les ADMINs peuvent créer
    public ResponseEntity<TrainSeatsAvailableDTO> createTrainSeatsAvailable(@Valid @RequestBody TrainSeatsAvailableDTO trainDto) {
        TrainSeatsAvailableDTO createdTrain = trainSeatsAvailableService.createTrainSeatsAvailable(trainDto);
        return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifie un train avec sièges disponibles",
                description = "Modifie un train avec ses informations dans la base de données")
    @ApiResponse(responseCode = "201", description = "Train modifié avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')") // Seuls les ADMINs peuvent modifier
    public ResponseEntity<TrainSeatsAvailableDTO> updateTrainSeatsAvailable(@PathVariable Long id, @Valid @RequestBody TrainSeatsAvailableDTO trainDetailsDto) {
        TrainSeatsAvailableDTO updatedTrain = trainSeatsAvailableService.updateTrainSeatsAvailable(id, trainDetailsDto);
        return ResponseEntity.ok(updatedTrain);
    }

    @Operation(summary = "Supprime un train avec ses sièges disponibles",
                description = "Supprime un train avec ses informations dans la base de données")
    @ApiResponse(responseCode = "204", description = "Train supprimé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')") // Seuls les ADMINs peuvent supprimer
    public ResponseEntity<Void> deleteTrainSeatsAvailable(@PathVariable Long id) {
        trainSeatsAvailableService.deleteTrainSeatsAvailable(id);
        return ResponseEntity.noContent().build(); // Renvoie un 204 No Content pour une suppression réussie
    }
}
