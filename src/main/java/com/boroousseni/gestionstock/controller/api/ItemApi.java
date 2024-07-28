package com.boroousseni.gestionstock.controller.api;

import java.util.List;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import com.boroousseni.gestionstock.dto.CustomerOrderLigneDto;
import com.boroousseni.gestionstock.dto.ItemDto;
import com.boroousseni.gestionstock.dto.SaleLigneDto;
import com.boroousseni.gestionstock.dto.SupplierOrderLigneDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@Tag(name = "items")
@RequestMapping(APP_ROOT)

public interface ItemApi {

	@PostMapping(value = "/items/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Enregistrer un article", description = "Cette méthode permet d'enregistrer ou modifier un article")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'objet article créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))),
			@ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide") })
	ItemDto save(@RequestBody ItemDto dto);

	@GetMapping(value = "/items/search/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher un article par son ID", description = "Cette méthode permet de chercher un article par son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la base de données avec l'ID fourni") })

	ItemDto findById(@PathVariable("idItem") Integer id);

	@GetMapping(value = "/items/filter/{codeItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher un article par CODE", description = "Cette méthode permet de chercher un article par son CODE")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'article a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la base de données avec le CODE fourni") })

	ItemDto findByCode(@PathVariable("codeItem") String codeItem);

	@GetMapping(value = "/items/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Renvoi la liste des articles", description = "Cette méthode permet de chercher et renvoyer la liste des articles qui existent dans la base de données")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La liste des articles ou une liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))) })

	List<ItemDto> findAll();

	@GetMapping(value = "/items/historique/vente/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Renvoi la liste des lignes de ventes pour un article donné", description = "Cette méthode permet de chercher et renvoyer la liste des lignes de ventes qui ont été effectuées pour un article donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La liste des lignes des ventes ou une liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))) })

	List<SaleLigneDto> findSaleHistory(@PathVariable("idItem") Integer idItem);

	@GetMapping(value = "/items/historique/commandeclient/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Renvoi la liste des lignes de commandes client pour un article donné", description = "Cette méthode permet de chercher et renvoyer la liste des lignes de commandes client qui ont été effectuées pour un article donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La liste des lignes de commandes ou une liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))) })

	List<CustomerOrderLigneDto> findCustomerOrderLigneHistory(@PathVariable("idItem") Integer idItem);

	@GetMapping(value = "/items/historique/commandefournisseur/{idItem}", produces = MediaType.APPLICATION_JSON_VALUE)

	@Operation(summary = "Renvoi la liste des lignes de commandes fournisseur pour un article donné", description = "Cette méthode permet de chercher et renvoyer la liste des lignes de commandes fournisseur qui ont été effectuées pour un article donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La liste des lignes de commandes ou une liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))) })

	List<SupplierOrderLigneDto> findSupplierOrderLigneHistory(@PathVariable("idItem") Integer idItem);

	@GetMapping(value = "/items/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Renvoi la liste des articles par catégorie", description = "Cette méthode permet de chercher et renvoyer la liste des articles par l'ID d'une catégori donné")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La liste des lignes de commandes ou une liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ItemDto.class))) })

	List<ItemDto> findAllItemByCategoryId(@PathVariable("idCategory") Integer idCategory);

	@DeleteMapping(value = "/items/delete/{idItem}")
	@Operation(summary = "Supprimer un article", description = "Cette méthode permet de supprimer un article par ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "L'article a été supprimé"),
			@ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la base de données avec l'ID fourni") })

	void delete(@PathVariable("idItem") Integer id);

}
