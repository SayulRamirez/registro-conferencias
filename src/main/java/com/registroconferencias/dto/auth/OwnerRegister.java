package com.registroconferencias.dto.auth;

import com.registroconferencias.dto.Address;
import com.registroconferencias.dto.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OwnerRegister extends User {

    @NotBlank(message = "el nombre no debe de ser nulo o estar en blanco")
    @Size(message = "el nombre debe de tener entre 2 y 50 caracteres", min = 2, max = 50)
    private final String name;

    @NotBlank(message = "el apellido no debe de ser nulo o estar en blanco")
    @Size(message = "el apellido debe de tener entre 2 y 60 caracteres", min = 2, max = 60)
    private final String lastname;

    @NotBlank(message = "el número de telefono no debe de ser nulo o estar en blanco")
    @Size(message = "el teléfono debe de tener entre 10 y 15 números", min = 10, max = 15)
    private final String phone;

    @NotNull(message = "la dirección no debe de ser nula")
    private final Address address;

    public OwnerRegister
            (String email, String password, String name, String lastname, String phone, Address address) {
        super(email, password);
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.address = address;
    }

    public @NotBlank(message = "el nombre no debe de ser nulo o estar en blanco") @Size(message = "el nombre debe de tener entre 2 y 50 caracteres", min = 2, max = 50)
    String getName() {
        return name;
    }

    public @NotBlank(message = "el apellido no debe de ser nulo o estar en blanco") @Size(message = "el apellido debe de tener entre 2 y 60 caracteres", min = 2, max = 60)
    String getLastname() {
        return lastname;
    }

    public @NotBlank(message = "el número de telefono no debe de ser nulo o estar en blanco") @Size(message = "el teléfono debe de tener entre 10 y 15 números", min = 10, max = 15)
    String getPhone() {
        return phone;
    }

    public @NotNull(message = "la dirección no debe de ser nula") Address getAddress() {
        return address;
    }
}
