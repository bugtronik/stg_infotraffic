package com.setrag.stg_infotraffic_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.setrag.stg_infotraffic_api.dto.TrainPlannedDTO;
import com.setrag.stg_infotraffic_api.dto.TrainSeatsAvailableDTO;
import com.setrag.stg_infotraffic_api.service.TrainPlannedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/trains-planned")
@Tag(name = "Trains Planned", description = "API pour la gestion des trains programmés durant le mois en cours")
public class TrainPlannedController {
    private final TrainPlannedService trainPlannedService;

    @Autowired
    public TrainPlannedController(TrainPlannedService trainPlannedService) {
        this.trainPlannedService = trainPlannedService;
    }

    @Operation(summary = "Récupérer tous les trains planifiés",
                description = "Permet de lister tous les trains programmés.")
    @ApiResponse(responseCode = "200", description = "Liste des trains récupérée avec succès",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TrainSeatsAvailableDTO.class)))
    @GetMapping
    public ResponseEntity<Page<TrainPlannedDTO>> getTrainsPlanned(
        @RequestParam(required = false) String trainNumber,
        @RequestParam(required = false) String classe,
        @PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable 
    ) {
        Page<TrainPlannedDTO> trainsPage = trainPlannedService.getTrainsPlanned(trainNumber, classe, pageable);
        return ResponseEntity.ok(trainsPage);
    }

    @Operation(summary = "Récupérer un train par ID",
                description = "Permet de récupérer les détails d'un train spécifique par son identifiant unique.")
    @ApiResponse(responseCode = "200", description = "Train récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Train non trouvé",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<TrainPlannedDTO> getTrainPlannedById(@PathVariable Long id) {
        TrainPlannedDTO train = trainPlannedService.getTrainPlannedById(id);
        return ResponseEntity.ok(train);
    }

    @Operation(summary = "Créer un nouveau train",
                description = "Ajoute un nouveau train avec ses informations à la base de données.")
    @ApiResponse(responseCode = "201", description = "Train/itinéraires créé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<TrainPlannedDTO> createTrainPlanned(@Valid @RequestBody TrainPlannedDTO trainDto) {
        TrainPlannedDTO createdTrain = trainPlannedService.createTrainPlanned(trainDto);
        return new ResponseEntity<>(createdTrain, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifie un train avec ses itinéraires",
                description = "Modifie un train avec ses informations dans la base de données")
    @ApiResponse(responseCode = "201", description = "Train modifié avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<TrainPlannedDTO> updateTrainPlanned(@PathVariable Long id, @Valid @RequestBody TrainPlannedDTO trainDetailsDto) {
        TrainPlannedDTO updatedTrain = trainPlannedService.updateTrainPlanned(id, trainDetailsDto);
        return ResponseEntity.ok(updatedTrain);
    }

    @Operation(summary = "Supprime un train",
                description = "Supprime un train avec ses informations dans la base de données.")
    @ApiResponse(responseCode = "204", description = "Train supprimé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainPlanned(@PathVariable Long id) {
        trainPlannedService.deleteTrainPlanned(id);
        return ResponseEntity.noContent().build();
    }
}
