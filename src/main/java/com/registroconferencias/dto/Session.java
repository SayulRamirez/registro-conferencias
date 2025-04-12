package com.registroconferencias.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Session (

        @Positive(message = "la sesión debe de ser un número mayor a 0")
        Long id_session,

        @NotNull(message = "la sala es requerida")
        @Positive(message = "la sala debe de ser un número mayor a 0")
        Long id_room,

        String name_room,

        @NotNull(message = "la fecha del evento es requerida")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,

        @NotNull(message = "la hora de inicio del evento es requerida")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime start_time,

        @NotNull(message = "el titulo de la sessión es requerido")
        @Size(message = "el titulo de la sessión debe de tener entre 5 y 100 caracteres", min = 5, max = 100)
        String title,

        @NotNull(message = "la descripción de la sessión es requerido")
        @Size(message = "la descripción de la sessión debe de tener entre 5 y 200 caracteres", min = 5, max = 200)
        String description,

        boolean sold_out,

        Address address
) {
}
