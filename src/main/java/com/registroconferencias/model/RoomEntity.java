package com.registroconferencias.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private Long capacity;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "openind_time")
    private LocalTime openingTime;

    @Column(name = "closing_time")
    private LocalTime closingTime;

    @Column(name = "available_hours")
    private Integer availableHours;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
