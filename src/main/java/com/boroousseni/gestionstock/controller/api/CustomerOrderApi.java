package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boroousseni.gestionstock.dto.CustomerOrderDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "commandes clients")
@RequestMapping(APP_ROOT)
public interface CustomerOrderApi {

	@PostMapping(value = "/commandclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Enregistrer une client", description = "Cette méthode permet d'enregistrer ou modifier un client")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'objet client créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerOrderDto.class))),
			@ApiResponse(responseCode = "400", description = "L'objet client n'est pas valide") })
	CustomerOrderDto save(@RequestBody CustomerOrderDto dto);

	@GetMapping(value = "/commandclients/search/{idCustomerOrder}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher une client par son ID", description = "Cette méthode permet de chercher un client par son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La commande client a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerOrderDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune  commande client n'existe dans la base de données avec l'ID fourni") })

	CustomerOrderDto findById(@PathVariable("idCustomerOrder") Integer id);

	@GetMapping(value = "/commandclients/update/{idCustomerOrder}/{orderLigneID}/{quantity}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Mettre à jour la quantité d'une commande client", description = "Cette méthode permet de mettre à jour la quantité d'une commande client via l'ID de la commande, l'ID de la ligne de commande et la quantité ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La commande client a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerOrderDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune  commande client n'existe dans la base de données avec l'ID fourni") })

	CustomerOrderDto updateOrderQuantity(@PathVariable("idCustomerOrder") Integer orderID,
			@PathVariable("orderLigneID") Integer orderLigneID, @PathVariable("quantity") Integer quantity);

	@GetMapping(value = "/commandclients/update/clients/{idCustomerOrder}/{idCustomer}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Mettre à jour les infos du client d'une commande client", description = "Cette méthode permet de mettre à jour les infos du client d'une commande client via l'ID de la commande, l'ID de la ligne du client ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La commande client a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerOrderDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune  commande client n'existe dans la base de données avec l'ID fourni") })

	CustomerOrderDto updateCustomer(@PathVariable("idCustomerOrder") Integer orderID,
			 @PathVariable("idCustomer") Integer idCustomer);
	
	@GetMapping(value = "/commandclients/update/clients/{idCustomerOrder}/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Mettre à jour l'article d'une commande client", description = "Cette méthode permet de mettre à jour l'article d'une commande client via l'ID de la commande et l'ID de l'article ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La commande client a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerOrderDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune  commande client n'existe dans la base de données avec l'ID fourni") })

	CustomerOrderDto updateItem(@PathVariable("idCustomerOrder") Integer orderID,
			 @PathVariable("idItem") Integer idCustomer);

	
	
	@GetMapping(value = "/commandclients/allByClient/{idCustomer}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Trouver la liste des commandes clients ", description = "Cette méthode permet de trouver la liste des commandes clients ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Commande client trouvée dans la base de données ou aucune client trouvée", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CustomerOrderDto.class))), })

	List<CustomerOrderDto> findAllByCustomerOrderId(@PathVariable("idCustomer") Integer id);
	
	
	

	@DeleteMapping(value = "/commandclients/delete/{idCustomerOrder}")
	@Operation(summary = "Supprimer un client", description = "Cette méthode permet de supprimer un client par ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "La commande client a été supprimé"),
			@ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la base de données avec l'ID fourni") })

	void delete(@PathVariable("idCustomerOrder") Integer id);
}
