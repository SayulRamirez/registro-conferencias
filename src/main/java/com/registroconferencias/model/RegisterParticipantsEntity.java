package com.registroconferencias.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "register_participants")
public class RegisterParticipantsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private ParticipantEntity participant;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private SessionEntity session;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    private boolean attended;
}
