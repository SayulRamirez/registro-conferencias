package com.registroconferencias.repositories.projections;

import java.time.LocalDateTime;

public interface ParticipantSession {

    Long getIdParticipant();

    String getName();

    String getLastname();

    boolean getAttended();

    LocalDateTime getRegisterDate();
}
