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

import com.setrag.stg_infotraffic_api.dto.TrainRouteDTO;
import com.setrag.stg_infotraffic_api.dto.TrainSeatsAvailableDTO;
import com.setrag.stg_infotraffic_api.service.TrainRouteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/trains-routes")
@Tag(name = "Trains Route", description = "API pour la gestion des itinéraires des trains programmés")
public class TrainRouteController {
    private final TrainRouteService trainRouteService;

    @Autowired
    public TrainRouteController(TrainRouteService trainRouteService) {
        this.trainRouteService = trainRouteService;
    }

    @Operation(summary = "Récupérer tous les trains avec ses itinéraires",
                description = "Permet de lister tous les trains avec l'ensemble des gares à parcourir.")
    @ApiResponse(responseCode = "200", description = "Liste des trains et itinéraires récupérée avec succès",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TrainSeatsAvailableDTO.class)))
    @GetMapping
    public ResponseEntity<Page<TrainRouteDTO>> getTrainRoutes(
        @Parameter(description = "Numéro de train à filtrer (recherche partielle insensible à la casse)")
        @RequestParam(required = false) String trainNumber,
        @Parameter(description = "Gare de départ à filtrer (recherche partielle insensible à la casse)")
        @RequestParam(required = false) String station,
        @PageableDefault(page = 0, size = 10, sort = "id")
        @Parameter(description = "Informations de pagination (page, size, sort). ?page=0&size=10&sort=trainNumber,asc")
        Pageable pageable
    ) {
        Page<TrainRouteDTO> routesPages = trainRouteService.getTrainRoutes(trainNumber, station, pageable);
        return ResponseEntity.ok(routesPages);
    }

    @Operation(summary = "Récupérer un train par ID",
                description = "Permet de récupérer les détails d'un train spécifique par son identifiant unique.")
    @ApiResponse(responseCode = "200", description = "Train récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "Train non trouvé",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<TrainRouteDTO> getTrainRouteById(@PathVariable Long id) {
        TrainRouteDTO route = trainRouteService.getTrainRouteById(id);
        return ResponseEntity.ok(route);
    }

    @Operation(summary = "Créer un nouveau train avec ses itinéraires",
                description = "Ajoute un nouveau train avec ses informations d'itinéraire à la base de données.")
    @ApiResponse(responseCode = "201", description = "Train/itinéraires créé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<TrainRouteDTO> createTrainRoute(@RequestBody TrainRouteDTO routeDto) {
        TrainRouteDTO createdRoute = trainRouteService.createTrainRoute(routeDto);
        return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifie un train avec ses itinéraires",
                description = "Modifie un train avec ses informations dans la base de données")
    @ApiResponse(responseCode = "201", description = "Train modifié avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<TrainRouteDTO> updateTrainRoute(@PathVariable Long id, @RequestBody TrainRouteDTO routeDetailsDto) {
        TrainRouteDTO updatedRoute = trainRouteService.updateTrainRoute(id, routeDetailsDto);
        return ResponseEntity.ok(updatedRoute);
    }

    @Operation(summary = "Supprime un train avec ses itinéraires",
                description = "Supprime un train avec ses informations dans la base de données")
    @ApiResponse(responseCode = "204", description = "Train supprimé avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide (erreur de validation)",
                content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = com.setrag.stg_infotraffic_api.exception.ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainRoute(@PathVariable Long id) {
        trainRouteService.deleteTrainRoute(id);
        return ResponseEntity.noContent().build();
    }
}
