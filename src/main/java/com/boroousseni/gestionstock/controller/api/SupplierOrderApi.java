package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.SupplierOrderDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;
import com.boroousseni.gestionstock.models.OrderStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "commandes fournisseurs")
@RequestMapping(APP_ROOT + "/commandfournisseurs")
public interface SupplierOrderApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une commande fournisseur", description = "Cette méthode permet d'enregistrer ou de modifier une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet commande fournisseur créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet commande fournisseur n'est pas valide")
    })
    SupplierOrderDto save(@RequestBody SupplierOrderDto dto);

    @PatchMapping(value = "/update/status/{idSupplierOrder}/{orderStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour le statut d'une commande fournisseur", description = "Cette méthode permet de mettre à jour le statut d'une commande fournisseur via l'ID de la commande et le statut")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le statut de la commande fournisseur a été mis à jour", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto updateOrderStatus(@PathVariable("idSupplierOrder") Integer orderID, @PathVariable("orderStatus") OrderStatus orderStatus);

    @PatchMapping(value = "/update/quantity/{idSupplierOrder}/{idOrderLine}/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour la quantité d'une commande fournisseur", description = "Cette méthode permet de mettre à jour la quantité d'une commande fournisseur via l'ID de la commande, l'ID de la ligne de commande et la quantité")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La quantité de la commande fournisseur a été mise à jour", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto updateOrderQuantity(@PathVariable("idSupplierOrder") Integer orderID,
                                         @PathVariable("idOrderLine") Integer idOrderLine, @PathVariable("quantity") Integer quantity);

    @PatchMapping(value = "/update/supplier/{idSupplierOrder}/{idSupplier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour le fournisseur d'une commande", description = "Cette méthode permet de mettre à jour les informations du fournisseur d'une commande via l'ID de la commande et l'ID du fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur de la commande a été mis à jour", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto updateSupplier(@PathVariable("idSupplierOrder") Integer orderID, @PathVariable("idSupplier") Integer supplierID);

    @PatchMapping(value = "/update/item/{idSupplierOrder}/{idOrderLine}/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre à jour un article d'une commande fournisseur", description = "Cette méthode permet de mettre à jour un article d'une commande via l'ID de la commande, l'ID de la ligne de commande et l'ID de l'article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article de la commande a été mis à jour", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto updateItem(@PathVariable("idSupplierOrder") Integer orderID, @PathVariable("idOrderLine") Integer orderLigneID, @PathVariable("itemId") Integer itemID);

    @DeleteMapping(value = "/delete/item/{idSupplierOrder}/{idOrderLine}")
    @Operation(summary = "Supprimer un article d'une commande fournisseur", description = "Cette méthode permet de supprimer un article d'une commande fournisseur via l'ID de la commande et l'ID de la ligne de commande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a été supprimé de la commande fournisseur"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto deleteItem(@PathVariable("idSupplierOrder") Integer orderID, @PathVariable("idOrderLine") Integer orderLigneID);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande fournisseur par ID", description = "Cette méthode permet de chercher une commande fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a été trouvée dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SupplierOrderDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    SupplierOrderDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister toutes les commandes fournisseurs", description = "Cette méthode permet de lister toutes les commandes fournisseurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste de commandes fournisseurs ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SupplierOrderDto.class)))
    })
    List<SupplierOrderDto> findAll();

    @GetMapping(value = "/orderlines/{idSupplierOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister toutes les lignes de commandes d'une commande fournisseur", description = "Cette méthode permet de lister toutes les lignes de commandes d'une commande fournisseur via l'ID de la commande")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des lignes de commandes ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SupplierOrderLigneDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    List<SupplierOrderLigneDto> findAllSupplierOrderLigneBySupplierOrderId(@PathVariable("idSupplierOrder") Integer orderID);

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Supprimer une commande fournisseur", description = "Cette méthode permet de supprimer une commande fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a été supprimée"),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la base de données avec l'ID fourni")
    })
    void delete(@PathVariable("id") Integer id);
}
