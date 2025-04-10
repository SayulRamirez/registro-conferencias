package com.registroconferencias.services;

import com.registroconferencias.dto.Session;

import java.util.List;

public interface SessionService {

    String create(Session session);

    void delete(Long id);

    List<Session> getAll(Long idRoom);

    Session get(Long idSession);
}
