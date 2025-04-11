package com.registroconferencias.services;

import com.registroconferencias.dto.Address;
import com.registroconferencias.dto.Room;
import com.registroconferencias.model.AddressEntity;
import com.registroconferencias.model.RoomEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.RoomRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    public RoomServiceImpl(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public String create(Room request) {

        if (request.address() == null)
            throw new IllegalArgumentException("La dirección es requerida");

        UserEntity user = userRepository.findById(request.user_id())
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe con el id: " + request.user_id()));

        roomRepository.save(new RoomEntity(null,
                request.name(),
                request.capacity(),
                new AddressEntity(null,
                        request.address().street(),
                        request.address().number(),
                        request.address().colony(),
                        request.address().zip_code(),
                        request.address().city(),
                        request.address().state()),
                request.openingTime(),
                request.closingTime(),
                request.availableHours(),
                user));

        return "Registro de la nueva sala exitoso";
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe una sala con el id: " + id);
        }
        roomRepository.deleteById(id);
    }

    @Override
    public List<Room> getAll(Long idUser) {

        List<RoomEntity> rooms = roomRepository.findAllByUserId(idUser);

        if (rooms.isEmpty()) return List.of();

        return rooms.stream().map(room ->
                new Room(room.getId(),
                room.getName(),
                room.getCapacity(),
                room.getOpeningTime(),
                room.getClosingTime(),
                room.getAvailableHours(),
                room.getUser().getId(),null)).toList();
    }

    @Override
    public Room get(Long idRoom) {

        RoomEntity room = roomRepository.findById(idRoom)
                .orElseThrow(() -> new EntityNotFoundException("no se encontro la sala con el id: " + idRoom));

        return new Room(room.getId(),
                room.getName(),
                room.getCapacity(),
                room.getOpeningTime(),
                room.getClosingTime(),
                room.getAvailableHours(),
                room.getUser().getId(),
                new Address(
                        room.getAddress().getStreet(),
                        room.getAddress().getNumber(),
                        room.getAddress().getColony(),
                        room.getAddress().getZipCode(),
                        room.getAddress().getCity(),
                        room.getAddress().getState()
                ));
    }
}
