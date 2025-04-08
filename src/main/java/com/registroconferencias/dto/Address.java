package com.registroconferencias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Address(

        @NotBlank(message = "la calle es requerida")
        @Size(message = "la calle debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
        String street,

        @NotBlank(message = "el número es requerido")
        @Size(message = "el número debe de tener entre 1 y 8 caracteres", min = 1, max = 8)
        String number,

        @NotBlank(message = "la colonia es requerida")
        @Size(message = "la colonia debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
        String colony,

        @NotBlank(message = "el código postal es requerido")
        @Size(message = "el código postal debe de tener entre 1 y 7 caracteres", min = 1, max = 7)
        String zip_code,

        @NotBlank(message = "la ciudad es requerida")
        @Size(message = "la ciudad debe de tener entre 2 y 50 caracteres", min = 2, max = 50)
        String city,

        @NotBlank(message = "el estado es requerido")
        @Size(message = "el estado debe de tener entre 2 y 50 caracteres", min = 2, max = 50)
        String state
){}
