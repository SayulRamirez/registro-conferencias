package com.registroconferencias.services;

import com.registroconferencias.dto.auth.OwnerRegister;

public interface AuthService {

    String login();

    /**
     * Registra un nuevo propietario
     * @param request {@link OwnerRegister} datos necesarios para el registro
     * @return mensaje de exito
     */
    String register(OwnerRegister request);
}
