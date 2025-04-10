package com.registroconferencias.controller;

import com.registroconferencias.dto.Session;
import com.registroconferencias.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody Session request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sessionService.create(request));
    }

    @DeleteMapping(value = "{idSession}")
    public ResponseEntity<Void> delete(@PathVariable Long idSession) {
        sessionService.delete(idSession);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "all/{idRoom}")
    public ResponseEntity<List<Session>> getAll(@PathVariable Long idRoom) {

        List<Session> sessions = sessionService.getAll(idRoom);
        if (sessions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(List.of());
        }

        return ResponseEntity.ok(sessions);
    }

    @GetMapping(value = "{idSession}")
    public ResponseEntity<Session> get(@PathVariable Long idSession) {
        return ResponseEntity.ok(sessionService.get(idSession));
    }
}
