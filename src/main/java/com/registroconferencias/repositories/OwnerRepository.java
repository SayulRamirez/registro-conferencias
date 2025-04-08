package com.registroconferencias.repositories;

import com.registroconferencias.model.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

    boolean existsByPhone(String phone);
}
