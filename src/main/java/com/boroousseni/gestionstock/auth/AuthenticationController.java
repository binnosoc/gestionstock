package com.boroousseni.gestionstock.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.boroousseni.gestionstock.dto.UserDto;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.ACCEPTED)
        @Operation(summary = "Enregistrer un nouvel utilisateur", description = "Cette méthode permet d'enregistrer un nouvel utilisateur.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "202", description = "Demande d'enregistrement acceptée", content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "400", description = "Requête invalide"),
                @ApiResponse(responseCode = "403", description = "Accès refusé")
        })
        public ResponseEntity<?> register(
                @RequestBody @Valid RegistrationRequest request
        ) throws MessagingException {
            service.register(request);
            return ResponseEntity.accepted().build();
        }

        @PostMapping("/authenticate")
        @Operation(summary = "Authentifier un utilisateur", description = "Cette méthode permet d'authentifier un utilisateur et de retourner un token JWT.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Authentification réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
                @ApiResponse(responseCode = "400", description = "Requête invalide"),
                @ApiResponse(responseCode = "403", description = "Accès refusé")
        })
        public ResponseEntity<AuthenticationResponse> authenticate(
                @RequestBody AuthenticationRequest request
        ) {
            return ResponseEntity.ok(service.authenticate(request));
        }

        @GetMapping("/activate-account")
        @Operation(summary = "Activer un compte utilisateur", description = "Cette méthode permet d'activer un compte utilisateur via un token envoyé par email.")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Compte activé avec succès"),
                @ApiResponse(responseCode = "400", description = "Requête invalide"),
                @ApiResponse(responseCode = "403", description = "Accès refusé")
        })
        public void confirm(
                @RequestParam String token
        ) throws MessagingException {
            service.activateAccount(token);
        }
        
        @GetMapping("/search")
        @Operation(summary = "Trouver l'utilisateur", description = "Cette méthode permet de trouver un  utilisateur via son email")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "utilisateur trouvé"),
                @ApiResponse(responseCode = "400", description = "Utilisateur non trouvé")
        })
        public UserDto findByEmail(
                @RequestParam String email
        ) throws MessagingException {
            return service.findByEmail(email);
        }
    }


}

