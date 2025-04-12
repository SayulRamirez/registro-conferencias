package com.registroconferencias.repositories;

import com.registroconferencias.model.RegisterParticipantsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterParticipantsRepository extends JpaRepository<RegisterParticipantsEntity, Long> {

    Optional<RegisterParticipantsEntity> findByParticipantIdAndSessionId(Long idParticipant, Long sessionId);

    @Query(value = """
    SELECT P.id as id_participant, P.name, P.lastname, R.attended, R.register_date
    FROM register_participants R
    INNER JOIN participants P ON R.participant_id = P.id
    WHERE R.session_id = :idSession
    """, nativeQuery = true)
    List<ParticipantSession> findAllParticipantsBySessionId(Long idSession);

    boolean existsByParticipantId(Long idParticipant);

    @Query(value = """
    SELECT S.sold_out FROM sessions S
    WHERE S.id = :idSession
    """, nativeQuery = true)
    boolean isSoldOut(@Param(value = "idSession") Long idSession);

    @Query(value = """
    SELECT S.capacity - COALESCE(COUNT(R.id), 0) AS resultado
    FROM register_participants R
    LEFT JOIN sessions S ON S.id = R.session_id
    WHERE R.session_id = :idSession;
    """, nativeQuery = true)
    Long availableSpaces(@Param(value = "idSession") Long idSession);
}
