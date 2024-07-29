package com.boroousseni.gestionstock.controller.api;

import static com.boroousseni.gestionstock.utils.Constants.APP_ROOT;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.boroousseni.gestionstock.dto.auth.AuthenticationRequest;
import com.boroousseni.gestionstock.dto.auth.AuthenticationResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "authentication")
@RequestMapping(APP_ROOT + "/auth")
public interface AuthenticationApi {

    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
