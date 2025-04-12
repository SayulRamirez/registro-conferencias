package com.registroconferencias.services;

import com.registroconferencias.dto.auth.LoginRequest;
import com.registroconferencias.dto.auth.LoginResponse;
import com.registroconferencias.dto.auth.RegisterAdminRequest;
import com.registroconferencias.dto.auth.RegisterRequest;
import com.registroconferencias.exceptions.UserNotActive;
import com.registroconferencias.model.Rol;
import com.registroconferencias.model.ParticipantEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.ParticipantRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ParticipantRepository participantRepository;

    public AuthServiceImpl(UserRepository userRepository, ParticipantRepository participantRepository) {
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest request) {

        UserEntity userEntity = userRepository.findByEmailAndPassword(request.email(), request.password())
                .orElseThrow(() -> new EntityNotFoundException("el usuario o contraseña son incorrectos"));

        if (!userEntity.isActive())
            throw new UserNotActive("el usuario no esta activo");

        return new LoginResponse(userEntity.getId(), userEntity.getRol().toString());
    }

    @Transactional
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

    @Transactional
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
