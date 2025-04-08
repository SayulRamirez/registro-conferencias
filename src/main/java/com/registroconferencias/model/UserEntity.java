package com.registroconferencias.model;

import com.registroconferencias.enumerate.Rol;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(unique = true, nullable = false, length = 100)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    private boolean active;
}
