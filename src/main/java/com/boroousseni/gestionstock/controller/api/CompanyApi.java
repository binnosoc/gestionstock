package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boroousseni.gestionstock.dto.CompanyDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "companies")
@RequestMapping(APP_ROOT)
public interface CompanyApi {
	@PostMapping(value = "/companies/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Enregistrer une entreprise", description = "Cette méthode permet d'enregistrer ou modifier une entreprise")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'objet entreprise créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))),
			@ApiResponse(responseCode = "400", description = "L'objet entreprise n'est pas valide") })
	CompanyDto save(@RequestBody CompanyDto dto);
	
	@GetMapping(value = "/entreprises/search/{idCompany}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Rechercher une entreprise par son ID", description = "Cette méthode permet de chercher un entreprise par son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "L'entreprise a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CompanyDto.class))),
			@ApiResponse(responseCode = "404", description = "Aucune entreprise n'existe dans la base de données avec l'ID fourni") })

	CompanyDto findById(@PathVariable("idCompany") Integer id);
}


