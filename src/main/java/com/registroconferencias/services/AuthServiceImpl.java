package com.registroconferencias.services;

import com.registroconferencias.dto.auth.*;
import com.registroconferencias.exceptions.UserNotActive;
import com.registroconferencias.jwt.JwtService;
import com.registroconferencias.model.Rol;
import com.registroconferencias.model.ParticipantEntity;
import com.registroconferencias.model.UserEntity;
import com.registroconferencias.repositories.ParticipantRepository;
import com.registroconferencias.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ParticipantRepository participantRepository;

    public AuthServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, ParticipantRepository participantRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException("el usuario no fue encontrado"));

        if (!user.isActive())
            throw new UserNotActive("el usuario no esta activo");

        String token = jwtService.getToken(user);

        return new AuthResponse(token);
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
                        passwordEncoder.encode(request.password()),
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
                        passwordEncoder.encode(request.password()),
                        Rol.ADMIN,
                        true));

        return "Registro exitoso";
    }
}
