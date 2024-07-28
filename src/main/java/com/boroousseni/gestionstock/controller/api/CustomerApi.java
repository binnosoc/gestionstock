package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.CustomerDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "clients")
@RequestMapping(APP_ROOT + "/clients")
public interface CustomerApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un client", description = "Cette méthode permet d'enregistrer ou de modifier un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet client créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet client n'est pas valide")
    })
    CustomerDto save(@RequestBody CustomerDto dto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par ID", description = "Cette méthode permet de chercher un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun client n'existe dans la base de données avec l'ID fourni")
    })
    CustomerDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister tous les clients", description = "Cette méthode permet de lister tous les clients enregistrés dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des clients ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerDto.class)))
    })
    List<CustomerDto> findAll();

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Supprimer un client", description = "Cette méthode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a été supprimé"),
            @ApiResponse(responseCode = "404", description = "Aucun client n'existe dans la base de données avec l'ID fourni")
    })
    void delete(@PathVariable("id") Integer id);
}
