package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.boroousseni.gestionstock.dto.ChangePasswordDto;
import com.boroousseni.gestionstock.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "utilisateurs")
@RequestMapping(APP_ROOT + "/utilisateurs")
public interface UserApi {

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un utilisateur", description = "Cette méthode permet d'enregistrer ou de modifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet utilisateur créé / modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet utilisateur n'est pas valide")
    })
    UserDto save(@RequestBody UserDto dto);

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par ID", description = "Cette méthode permet de chercher un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la base de données avec l'ID fourni")
    })
    UserDto findById(@PathVariable("id") Integer id);

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister tous les utilisateurs", description = "Cette méthode permet de lister tous les utilisateurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs ou liste vide", content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = UserDto.class)))
    })
    List<UserDto> findAll();

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Supprimer un utilisateur", description = "Cette méthode permet de supprimer un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a été supprimé"),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la base de données avec l'ID fourni")
    })
    void delete(@PathVariable("id") Integer id);

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par email", description = "Cette méthode permet de chercher un utilisateur par email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a été trouvé dans la base de données", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la base de données avec l'email fourni")
    })
    UserDto findByEmail(@PathVariable("email") String email);

    @PostMapping(value = "/changepassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changer le mot de passe d'un utilisateur", description = "Cette méthode permet de changer le mot de passe d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le mot de passe de l'utilisateur a été modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Les informations fournies pour changer le mot de passe ne sont pas valides")
    })
    UserDto changePassword(@RequestBody ChangePasswordDto dto);
}
