package com.registroconferencias.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Participant(

        @NotNull(message = "el id del participante es requerido")
        @Positive(message = "el id del participante debe de ser mayor a 0")
        Long id_participant,

        @NotNull(message = "el id de la sesión es requerido")
        @Positive(message = "el id de la sesión debe de ser mayor a 0")
        Long id_session,

        Boolean attended
){}
