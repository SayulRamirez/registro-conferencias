package com.registroconferencias.controller;

import com.registroconferencias.dto.auth.OwnerRegister;
import com.registroconferencias.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "owner")
    public ResponseEntity<String> registerOwner(@Valid @RequestBody OwnerRegister request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
