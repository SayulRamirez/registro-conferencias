package com.registroconferencias.controller;

import com.registroconferencias.dto.Room;
import com.registroconferencias.services.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Registra o crea una nueva sala para dar conferencias o sesiones (solo como admin)",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Room request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(request));
    }

    @Operation(summary = "Elimina una sala (solo como admin)", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obten todas las salas disponibles (cualquier usuario autenticado)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "")
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @Operation(summary = "Obten los detalles de una sala (cualquier usuario autenticado)", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "{idRoom}")
    public ResponseEntity<Room> get(@PathVariable Long idRoom) {
        return ResponseEntity.ok(roomService.get(idRoom));
    }
}
