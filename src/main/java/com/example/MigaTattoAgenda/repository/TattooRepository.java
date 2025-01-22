package com.example.MigaTattoAgenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MigaTattoAgenda.entity.Tattoo;

public interface TattooRepository extends JpaRepository<Tattoo, Long> {
    List<Tattoo> findByCostumerId(Long costumerId);
}
