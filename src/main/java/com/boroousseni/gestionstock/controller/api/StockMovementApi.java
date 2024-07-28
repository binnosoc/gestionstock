package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.StockMovementDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "mouvements de stock")
@RequestMapping(APP_ROOT + "/mouvements")
public interface StockMovementApi {

    @GetMapping(value = "/stockreel/{itemID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Calculer le stock réel d'un article", description = "Cette méthode permet de calculer le stock réel d'un article donné en utilisant son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le stock réel de l'article a été calculé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la base de données avec l'ID fourni")
    })
    BigDecimal stockReelItem(@PathVariable("itemID") Integer itemID);

    @GetMapping(value = "/{itemID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister les mouvements de stock d'un article", description = "Cette méthode permet de lister tous les mouvements de stock pour un article donné en utilisant son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des mouvements de stock ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = StockMovementDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la base de données avec l'ID fourni")
    })
    List<StockMovementDto> stockMovementItem(@PathVariable("itemID") Integer itemID);

    @PostMapping(value = "/entree", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une entrée de stock", description = "Cette méthode permet d'enregistrer une entrée de stock pour un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entrée de stock a été enregistrée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockMovementDto.class))),
            @ApiResponse(responseCode = "400", description = "Les informations fournies pour l'entrée de stock ne sont pas valides")
    })
    StockMovementDto entreeStock(@RequestBody StockMovementDto dto);

    @PostMapping(value = "/sortie", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une sortie de stock", description = "Cette méthode permet d'enregistrer une sortie de stock pour un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La sortie de stock a été enregistrée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockMovementDto.class))),
            @ApiResponse(responseCode = "400", description = "Les informations fournies pour la sortie de stock ne sont pas valides")
    })
    StockMovementDto sortieStock(@RequestBody StockMovementDto dto);

    @PostMapping(value = "/correctionPos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Corriger le stock en positif", description = "Cette méthode permet de corriger le stock en augmentant la quantité pour un article donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La correction positive de stock a été enregistrée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockMovementDto.class))),
            @ApiResponse(responseCode = "400", description = "Les informations fournies pour la correction positive ne sont pas valides")
    })
    StockMovementDto correctionStockPos(@RequestBody StockMovementDto dto);

    @PostMapping(value = "/correctionNeg", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Corriger le stock en négatif", description = "Cette méthode permet de corriger le stock en diminuant la quantité pour un article donné")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La correction négative de stock a été enregistrée", content = @Content(mediaType = "application/json", schema = @Schema(implementation = StockMovementDto.class))),
            @ApiResponse(responseCode = "400", description = "Les informations fournies pour la correction négative ne sont pas valides")
    })
    StockMovementDto correctionStockNeg(@RequestBody StockMovementDto dto);
}
