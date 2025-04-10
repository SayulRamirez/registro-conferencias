package com.registroconferencias.services;

import com.registroconferencias.dto.auth.LoginRequest;
import com.registroconferencias.dto.auth.LoginResponse;
import com.registroconferencias.dto.auth.RegisterAdminRequest;
import com.registroconferencias.dto.auth.RegisterRequest;
import com.registroconferencias.enumerate.Rol;
import com.registroconferencias.model.ParticipantEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.ParticipantRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ParticipantRepository participantRepository;

    public AuthServiceImpl(UserRepository userRepository, ParticipantRepository participantRepository) {
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email()))
            throw new EntityExistsException("El correo ya se encuentra registrado");

        participantRepository.save(new ParticipantEntity(null,
                request.name(),
                request.lastname(),
                new UserEntity(null,
                        request.email(),
                        request.password(),
                        Rol.PARTICIPANTE,
                        true)));

        return "Registro exitoso";
    }

    @Override
    public String register(RegisterAdminRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new EntityExistsException("El correo ya se encuentra registrado");

        userRepository.save(
                new UserEntity(null, request.email(),
                        request.password(),
                        Rol.ADMIN,
                        true));

        return "Registro exitoso";
    }
}
