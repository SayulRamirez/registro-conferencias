package com.registroconferencias.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    private boolean active;

    @Column(length = 100)
    private String title;

    @Column(length = 200)
    private String description;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "sold_out")
    private boolean soldOut;

    public SessionEntity() {
    }

    public SessionEntity
            (Long id, RoomEntity room, LocalDate date, LocalTime startTime, boolean active, String title, String description, LocalDateTime registerDate, boolean soldOut) {
        this.id = id;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.active = active;
        this.title = title;
        this.description = description;
        this.registerDate = registerDate;
        this.soldOut = soldOut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }
}
