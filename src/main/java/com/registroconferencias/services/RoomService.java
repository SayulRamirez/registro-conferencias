package com.registroconferencias.services;

import com.registroconferencias.dto.Room;

import java.util.List;

public interface RoomService {

    String create(Room request);

    void delete(Long id);

    List<Room> getAll();

    Room get(Long idRoom);
}
