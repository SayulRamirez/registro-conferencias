package com.registroconferencias.services;

import com.registroconferencias.dto.register.participant.ParticipantResponse;
import com.registroconferencias.dto.register.participant.Participant;
import com.registroconferencias.exceptions.SessionSoldOutException;
import com.registroconferencias.model.ParticipantEntity;
import com.registroconferencias.model.RegisterParticipantsEntity;
import com.registroconferencias.model.SessionEntity;
import com.registroconferencias.repositories.ParticipantRepository;
import com.registroconferencias.repositories.RegisterParticipantsRepository;
import com.registroconferencias.repositories.SessionRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterParticipantsSeviceImpl implements RegisterParticipantsService {

    private final RegisterParticipantsRepository registerRepository;

    private final ParticipantRepository participantRepository;

    private final SessionRepository sessionRepository;

    public RegisterParticipantsSeviceImpl(RegisterParticipantsRepository registerRepository, ParticipantRepository participantRepository, SessionRepository sessionRepository) {
        this.registerRepository = registerRepository;
        this.participantRepository = participantRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    @Override
    public String register(Participant participant) {

        SessionEntity session = sessionRepository.findById(participant.id_session())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la sesión a inscribir"));

        if (registerRepository.isSoldOut(participant.id_session()))
            throw new SessionSoldOutException("La sesión esta llena");

        if (registerRepository.existsByParticipantId(participant.id_participant()))
            throw new EntityExistsException("Ya estas registrado para esta sesión");

        ParticipantEntity participantEntity = participantRepository.findById(participant.id_participant())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el participante con id: " + participant.id_participant()));

        registerRepository.save(new RegisterParticipantsEntity(null,
                participantEntity,
                session,
                LocalDateTime.now(),
                false));

        Long availableSpaces = registerRepository.availableSpaces(participant.id_session());

        if (availableSpaces == 0) {
            session.setSoldOut(true);
            sessionRepository.save(session);
        }

        return "Registro completado";
    }

    @Override
    public List<ParticipantResponse> getParticipants(Long idSession) {

        return registerRepository.findAllBySessionId(idSession).stream()
                .map(participant -> new ParticipantResponse(participant.getId(),
                        participant.getParticipant().getName(),
                        participant.getParticipant().getLastname(),
                        participant.isAttended(),
                        participant.getRegisterDate()))
                .toList();
    }

    @Transactional
    @Override
    public String markAttendance(Participant participant) {

        RegisterParticipantsEntity register = registerRepository.findByParticipantId(participant.id_participant())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el participante con id: " + participant.id_participant()));

        register.setAttended(participant.attended() == null ? false : participant.attended());

        registerRepository.save(register);

        return "Asistencia actualizada";
    }
}
