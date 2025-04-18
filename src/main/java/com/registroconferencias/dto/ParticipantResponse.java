package com.registroconferencias.dto;

import java.time.LocalDateTime;

public record ParticipantResponse (

        Long id_participant,

        String name,

        String lastname,

        boolean attended,

        LocalDateTime register_date){
}
