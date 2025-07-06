package com.example.MigaTattoAgenda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.MigaTattoAgenda.entity.Session;
import com.example.MigaTattoAgenda.projections.LastSessionProjection;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query(value = """
            SELECT s.costumer_id, c.costumer_name, s.tattoo_id, t.tattoo_name, s.session_date
            FROM sessions s
            JOIN costumers c ON s.costumer_id = c.id
            JOIN tattoos t ON s.tattoo_id = t.id
            WHERE s.costumer_id = :costumerId
            ORDER BY s.session_date DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<LastSessionProjection> findLastSessionByCostumerId(@Param("costumerId") Long costumerId);

    @Query(value = """
                        SELECT
                c.id AS costumer_id,
                c.costumer_name,
                c.costumer_lastname,
                s.tattoo_id,
                t.tattoo_name,
                s.session_date
            FROM costumers c
            LEFT JOIN (
                SELECT s1.*
                FROM sessions s1
                JOIN (
                    SELECT costumer_id, MAX(session_date) AS max_date
                    FROM sessions
                    GROUP BY costumer_id
                ) latest ON s1.costumer_id = latest.costumer_id AND s1.session_date = latest.max_date
            ) s ON c.id = s.costumer_id
            LEFT JOIN tattoos t ON s.tattoo_id = t.id
            ORDER BY
                CASE WHEN s.session_date IS NULL THEN 1 ELSE 0 END,
                COALESCE(s.session_date, c.created_at) DESC
                                                """, nativeQuery = true)
    List<LastSessionProjection> findLastSessionsOfAllCustomers();

}
