package com.example.MigaTattoAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MigaTattoAgenda.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
