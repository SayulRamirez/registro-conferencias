package com.registroconferencias.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Room(

        @Positive(message = "la sala debe de ser un número mayor a 0")
        Long id_room,

        @NotNull(message = "el nombre del recinto es requerido")
        @Size(message = "el nombre del recinto debe de tener entre 5 y 50 caracteres", min = 5, max = 50)
        String name,

        @NotNull(message = "la capacidad es requerida")
        @Positive(message = "la capacidad debe de ser un número mayor a 0")
        Long capacity,

        @NotNull(message = "la hora de apertura es requerida")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime openingTime,

        @NotNull(message = "la hora de cierre es requerida")
        @JsonFormat(pattern = "HH:mm:ss")
        LocalTime closingTime,

        @NotNull(message = "las horas de disposición son requeridas")
        @Positive(message = "las horas disponibles deben de ser mayor a 1")
        Integer availableHours,

        @NotNull(message = "el usuario es requerido")
        @Positive(message = "la capacidad debe de ser un número mayor a 0")
        Long user_id,

        Address address
){}
