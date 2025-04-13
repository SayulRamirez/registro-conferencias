package com.registroconferencias.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "el email es requerido")
        String email,

        @NotBlank(message = "la contaseña es requerida")
        String password
) {
}
