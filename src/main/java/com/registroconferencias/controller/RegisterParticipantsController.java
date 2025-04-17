package com.registroconferencias.controller;

import com.registroconferencias.dto.Participant;
import com.registroconferencias.dto.ParticipantResponse;
import com.registroconferencias.services.RegisterParticipantsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/participant")
@RequiredArgsConstructor
public class RegisterParticipantsController {

    private final RegisterParticipantsService registerService;

    @Operation(summary = "Registra un participante a una sesión (solo como participante)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody Participant request) {
        return ResponseEntity.ok(registerService.register(request));
    }

    @Operation(summary = "Obten la lista de los participantes de una sesión (solo como admin)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "{idSession}")
    public ResponseEntity<List<ParticipantResponse>> getAll(@PathVariable Long idSession) {
        return ResponseEntity.ok(registerService.getAll(idSession));
    }

    @Operation(summary = "Marca la asistencia de un participante (solo como admin)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping
    public ResponseEntity<String> markAttendance(@Valid @RequestBody Participant request) {
        return ResponseEntity.ok(registerService.markAttendance(request));
    }
}
