package com.registroconferencias.controller;

import com.registroconferencias.dto.register.participant.Participant;
import com.registroconferencias.dto.register.participant.ParticipantResponse;
import com.registroconferencias.services.RegisterParticipantsService;
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

    @PostMapping
    public ResponseEntity<String> register(@Valid @RequestBody Participant request) {
        return ResponseEntity.ok(registerService.register(request));
    }

    @GetMapping(value = "{idSession}")
    public ResponseEntity<List<ParticipantResponse>> getAll(@PathVariable Long idSession) {
        List<ParticipantResponse> participants = registerService.getParticipants(idSession);

        if (participants.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok(participants);
    }

    @PutMapping
    public ResponseEntity<String> markAttendance(@Valid @RequestBody Participant request) {
        return ResponseEntity.ok(registerService.markAttendance(request));
    }
}
