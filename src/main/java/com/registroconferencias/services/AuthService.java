package com.registroconferencias.services;

import com.registroconferencias.dto.auth.*;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    String register(RegisterRequest request);

    String register(RegisterAdminRequest request);
}
