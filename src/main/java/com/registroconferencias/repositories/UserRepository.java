package com.registroconferencias.repositories;

import com.registroconferencias.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Verifica si existe un usuario por el email
     * @param email email del usuario
     * @return true si ya se encuentra registrado el email o false si no
     */
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
