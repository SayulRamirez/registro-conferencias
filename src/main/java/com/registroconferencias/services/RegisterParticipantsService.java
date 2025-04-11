package com.registroconferencias.services;

import com.registroconferencias.dto.register.participant.ParticipantResponse;
import com.registroconferencias.dto.register.participant.Participant;

import java.util.List;

public interface RegisterParticipantsService {

    String register(Participant participant);

    List<ParticipantResponse> getParticipants(Long idSession);

    String markAttendance(Participant participant);
}
