package com.registroconferencias.controller;

import com.registroconferencias.dto.auth.*;
import com.registroconferencias.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Ingresa a la aplicación")
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Crea una nueva cuenta de usuario como participante")
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Operation(summary = "Regista un nuevo administrador, esto solo se puede logeado como administrador",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(value = "admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterAdminRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
