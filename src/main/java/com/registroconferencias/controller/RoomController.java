package com.registroconferencias.controller;

import com.registroconferencias.dto.room.Room;
import com.registroconferencias.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Room request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(request));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "all/{idUser}")
    public ResponseEntity<List<Room>> getAll(@PathVariable Long idUser) {
        return ResponseEntity.ok(roomService.getAll(idUser));
    }

    @GetMapping(value = "{idRoom}")
    public ResponseEntity<Room> get(@PathVariable Long idRoom) {
        return ResponseEntity.ok(roomService.get(idRoom));
    }
}
