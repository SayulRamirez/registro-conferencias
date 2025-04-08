package com.registroconferencias.repositories;

import com.registroconferencias.model.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

    /**
     * Verifica si existe un propietario por su numero de telefono
     * @param phone numero de telefono
     * @return true si ya esta registrado el numero de telefono o false si no
     */
    boolean existsByPhone(String phone);
}
