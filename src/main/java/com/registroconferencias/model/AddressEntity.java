package com.registroconferencias.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 8)
    private String number;

    @Column(nullable = false, length = 50)
    private String colony;

    @Column(name = "zip_code", nullable = false, length = 7)
    private String zipCode;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String state;
}
