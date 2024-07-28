package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boroousseni.gestionstock.dto.CategoryDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "categories")
@RequestMapping(APP_ROOT)
public interface CategoryApi {
	@PostMapping(value = "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Enregistrer une catégorie", description = "Cette méthode permet d'enregistrer ou modifier une catégorie d'catégorie")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'objet catégorie créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
			@ApiResponse(responseCode = "400", description = "L'objet catégorie n'est pas valide") })
	CategoryDto save(@RequestBody CategoryDto dto);
	
	@GetMapping(value = "/categories/search/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher une catégorie par son ID", description = "Cette méthode permet de chercher un catégorie par son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'catégorie a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CategoryDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la base de données avec l'ID fourni") })

	CategoryDto findById(@PathVariable("idCategory") Integer id);
	
	@GetMapping(value = "/categories/search/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher une catégorie par son code", description = "Cette méthode permet de chercher un catégorie par son code")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "La catégorie a été trouvée dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CategoryDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune catégorie n'existe dans la base de données avec l'ID fourni") })

	CategoryDto findByCode(@PathVariable("code") String code);
	

	@GetMapping(value = "/categories/all/{idCompany}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Trouver la liste des catégories d'article", description = "Cette méthode permet de trouver la liste des catégories des articles  pour entreprise")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Catégorie trouvée dans la base de données ou aucune catégorie trouvée", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CategoryDto.class))),
			 })

	List<CategoryDto>  findAllByCompanyId(@PathVariable("idCompany") Integer id);

}
