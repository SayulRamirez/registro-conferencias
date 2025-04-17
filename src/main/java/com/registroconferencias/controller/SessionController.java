package com.registroconferencias.controller;

import com.registroconferencias.dto.Session;
import com.registroconferencias.services.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @Operation(summary = "Crea o registra una nueva sesión (solo como admin)", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Session request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessionService.create(request));
    }

    @Operation(summary = "Elimina una sesión (solo como admin)", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(value = "{idSession}")
    public ResponseEntity<Void> delete(@PathVariable Long idSession) {
        sessionService.delete(idSession);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obten todas las sesiones correspondientes a una sala (cualquier usuario autenticado)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "all/{idRoom}")
    public ResponseEntity<List<Session>> getAll(@PathVariable Long idRoom) {
        return ResponseEntity.ok(sessionService.getAll(idRoom));
    }

    @Operation(summary = "Obten los detalles de una sesión (cualquier usuario autenticado)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "{idSession}")
    public ResponseEntity<Session> get(@PathVariable Long idSession) {
        return ResponseEntity.ok(sessionService.get(idSession));
    }
}
