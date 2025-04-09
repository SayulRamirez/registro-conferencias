package com.registroconferencias.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

    @NotBlank(message = "el email es requerido")
    @Size(message = "el email debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
    String email,

    @NotBlank(message = "la contaseña es requerida")
    @Size(message = "la contraseña debe de tener entre 8 y 50 caracteres", min = 8, max = 50)
    String password,

    @NotBlank(message = "el nombre es requerido")
    @Size(message = "el nombre debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
    String name,

    @NotBlank(message = "el apellido es requerido")
    @Size(message = "el apellido debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
    String lastname
){}
