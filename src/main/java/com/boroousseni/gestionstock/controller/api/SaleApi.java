package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.SaleDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;

@Tag(name = "ventes")
@RequestMapping(APP_ROOT + "/ventes")
public interface SaleApi {

	@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une vente", description = "Cette méthode permet d'enregistrer ou de modifier une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet vente créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaleDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet vente n'est pas valide")
    })
    SaleDto save(@RequestBody SaleDto dto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par ID", description = "Cette méthode permet de chercher une vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été trouvée dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaleDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la base de données avec l'ID fourni")
    })
    SaleDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par code", description = "Cette méthode permet de chercher une vente par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été trouvée dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SaleDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la base de données avec le code fourni")
    })
    SaleDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister toutes les ventes", description = "Cette méthode permet de lister toutes les ventes enregistrées dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des ventes ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SaleDto.class)))
    })
    List<SaleDto> findAll();

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Supprimer une vente", description = "Cette méthode permet de supprimer une vente par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a été supprimée"),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la base de données avec l'ID fourni")
    })
    void delete(@PathVariable("id") Integer id);
}
