package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.SupplierDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "fournisseurs")
@RequestMapping(APP_ROOT + "/fournisseurs")
public interface SupplierApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un fournisseur", description = "Cette méthode permet d'enregistrer ou de modifier un fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet fournisseur créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet fournisseur n'est pas valide")
    })
    SupplierDto save(@RequestBody SupplierDto dto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un fournisseur par ID", description = "Cette méthode permet de chercher un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister tous les fournisseurs", description = "Cette méthode permet de lister tous les fournisseurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de fournisseurs ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SupplierDto.class)))
    })
    List<SupplierDto> findAll();

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Supprimer un fournisseur", description = "Cette méthode permet de supprimer un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a été supprimé"),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    void delete(@PathVariable("id") Integer id);
}
