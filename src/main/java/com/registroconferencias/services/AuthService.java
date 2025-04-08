package com.registroconferencias.services;

import com.registroconferencias.dto.auth.OwnerRegister;

public interface AuthService {

    String login();

    String register(OwnerRegister request);
}
