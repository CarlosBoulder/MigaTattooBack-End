package com.example.MigaTattoAgenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.MigaTattoAgenda.entity.Costumer;
import com.example.MigaTattoAgenda.projections.CostumerProjection;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
    @Query(value = "SELECT id, birth_date, costumer_lastname, costumer_name, email, phone FROM costumers WHERE costumer_name LIKE CONCAT('%', :costumerName, '%')", nativeQuery = true)
    List<CostumerProjection> findCostumerByName(@Param("costumerName") String costumerName);

    @Query(value = "SELECT id, birth_date, costumer_lastname, costumer_name, email, phone FROM costumers ORDER BY created_at DESC", nativeQuery = true)
    List<CostumerProjection> findAllByOrderByCreatedAtDesc();
}
