package com.registroconferencias.services;

import com.registroconferencias.dto.auth.*;
import com.registroconferencias.exceptions.UserNotActiveException;
import com.registroconferencias.jwt.JwtService;
import com.registroconferencias.model.Rol;
import com.registroconferencias.model.ParticipantEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.ParticipantRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ParticipantRepository participantRepository;

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException("el usuario no fue encontrado"));

        if (!user.isActive())
            throw new UserNotActiveException("el usuario no esta activo");

        String token = jwtService.getToken(user);

        return new AuthResponse(token);
    }

    @Transactional
    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email()))
            throw new EntityExistsException("El correo ya se encuentra registrado");

        participantRepository.save(ParticipantEntity.builder()
                .name(request.name())
                .lastname(request.lastname())
                .user( UserEntity.builder()
                        .email(request.email())
                        .password(passwordEncoder.encode(request.password()))
                        .rol(Rol.PARTICIPANTE)
                        .active(true).build())
                .build()
        );

        return "Registro exitoso";
    }

    @Transactional
    @Override
    public String register(RegisterAdminRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new EntityExistsException("El correo ya se encuentra registrado");

        userRepository.save( UserEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .rol(Rol.ADMIN)
                .active(true)
                .build()
        );

        return "Registro exitoso";
    }
}
