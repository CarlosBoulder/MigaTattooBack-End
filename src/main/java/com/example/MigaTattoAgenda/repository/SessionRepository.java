package com.example.MigaTattoAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MigaTattoAgenda.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
