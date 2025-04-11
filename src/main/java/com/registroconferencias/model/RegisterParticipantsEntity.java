package com.registroconferencias.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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

    public RegisterParticipantsEntity() {
    }

    public RegisterParticipantsEntity(Long id, ParticipantEntity participant, SessionEntity session, LocalDateTime registerDate, boolean attended) {
        this.id = id;
        this.participant = participant;
        this.session = session;
        this.registerDate = registerDate;
        this.attended = attended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParticipantEntity getParticipant() {
        return participant;
    }

    public void setParticipant(ParticipantEntity participant) {
        this.participant = participant;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
