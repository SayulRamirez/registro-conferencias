package com.registroconferencias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "el email es requerido")
    @Size(message = "el email debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
    private final String email;

    @NotBlank(message = "la contaseña es requerida")
    @Size(message = "la contraseña debe de tener entre 8 y 50 caracteres", min = 8, max = 50)
    private final String password;

    private final boolean active;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.active = true;
    }

    public @NotBlank(message = "el email es requerido") @Size(message = "el email debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
    String getEmail() {
        return email;
    }

    public @NotBlank(message = "la contaseña es requerida") @Size(message = "la contraseña debe de tener entre 8 y 50 caracteres", min = 8, max = 50)
    String getPassword() {
        return password;
    }

    public boolean getActive() {
        return active;
    }
}
