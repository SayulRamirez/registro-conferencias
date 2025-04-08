package com.registroconferencias.services;

import com.registroconferencias.dto.auth.OwnerRegister;
import com.registroconferencias.enumerate.Rol;
import com.registroconferencias.model.AddressEntity;
import com.registroconferencias.model.OwnerEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.OwnerRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final OwnerRepository ownerRepository;

    private final UserRepository userRepository;

    public AuthServiceImpl(OwnerRepository ownerRepository, UserRepository userRepository) {
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String login() {
        return "";
    }

    @Override
    public String register(OwnerRegister request) {

        if (userRepository.existsByEmail(request.getEmail()))
            throw new EntityExistsException("el email ya esta registrado");

        if (ownerRepository.existsByPhone(request.getPhone()))
            throw new EntityExistsException("el teléfono ya esta registrado");

        ownerRepository.save(
                new OwnerEntity(null,
                        request.getName(),
                        request.getLastname(),
                        request.getPhone(),
                        new UserEntity(null,
                                request.getEmail(),
                                request.getPassword(),
                                Rol.PROPIETARIO,
                                request.getActive()),
                        new AddressEntity(null,
                                request.getAddress().street(),
                                request.getAddress().number(),
                                request.getAddress().colony(),
                                request.getAddress().zip_code(),
                                request.getAddress().city(),
                                request.getAddress().state())
                )
        );

        return "Registro exitoso";
    }
}
