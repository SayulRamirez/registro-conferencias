package com.registroconferencias.repositories;

import com.registroconferencias.model.SessionEntity;
import com.registroconferencias.repositories.projections.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    List<SessionEntity> findAllByRoomId(Long idRoom);

    @Query(value = """
        SELECT
            S.id AS id_session, R.id AS id_room, R.name AS name_room,
            S.date, S.start_time, S.title, S.description, S.sold_out,
            A.street, A.number, A.colony, A.zip_code, A.city, A.state
        FROM sessions S
        INNER JOIN rooms R ON S.room_id = R.id
        INNER JOIN address A ON R.address_id = A.id
        WHERE S.id = :sessionId
    """, nativeQuery = true)
    Optional<SessionInfo> findBySessionId(@Param("sessionId") Long sessionId);
}
