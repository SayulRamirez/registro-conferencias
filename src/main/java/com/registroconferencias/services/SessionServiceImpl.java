package com.registroconferencias.services;

import com.registroconferencias.dto.Address;
import com.registroconferencias.dto.Session;
import com.registroconferencias.model.RoomEntity;
import com.registroconferencias.model.SessionEntity;
import com.registroconferencias.repositories.RoomRepository;
import com.registroconferencias.repositories.projections.SessionInfo;
import com.registroconferencias.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final RoomRepository roomRepository;

    @Transactional
    @Override
    public String create(Session session) {

        RoomEntity room = roomRepository.findById(session.id_room())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro la sala con id: " + session.id_room()));

        LocalDateTime now = LocalDateTime.now();

        if (session.date().isBefore(now.toLocalDate()))
            throw new IllegalArgumentException("La fecha no debe de ser menor a la fehca actual: " + now);

        sessionRepository.save( SessionEntity.builder()
                .room(room)
                .date(session.date())
                .startTime(session.start_time())
                .active(true)
                .title(session.title())
                .description(session.description())
                .registerDate(now)
                .capacity(room.getCapacity())
                .soldOut(false).build()
        );

        return "Registro de la sesión exitoso";
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!sessionRepository.existsById(id))
            throw new EntityNotFoundException("No se encontro la sala a eliminar con el id: " + id);

        sessionRepository.deleteById(id);
    }

    @Override
    public List<Session> getAll(Long idRoom) {

        return sessionRepository.findAllByRoomId(idRoom).stream()
                .map(session -> new Session(session.getId(),
                        session.getRoom().getId(),
                        session.getRoom().getName(),
                        session.getDate(),
                        session.getStartTime(),
                        session.getTitle(),
                        session.getDescription(),
                        session.isSoldOut(), null)).toList();
    }

    @Override
    public Session get(Long idSession) {

        SessionInfo session = sessionRepository.findBySessionId(idSession)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró la sesión con id: " + idSession));

        return new Session(
                session.getIdSession(),
                session.getIdRoom(),
                session.getNameRoom(),
                session.getDate(),
                session.getStartTime(),
                session.getTitle(),
                session.getDescription(),
                session.isSoldOut(),
                new Address(
                        session.getStreet(),
                        session.getNumber(),
                        session.getColony(),
                        session.getZipCode(),
                        session.getCity(),
                        session.getState()
                )
        );
    }
}
