package com.registroconferencias.services;

import com.registroconferencias.dto.auth.LoginRequest;
import com.registroconferencias.dto.auth.LoginResponse;
import com.registroconferencias.dto.auth.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    String register(RegisterRequest request);
}
