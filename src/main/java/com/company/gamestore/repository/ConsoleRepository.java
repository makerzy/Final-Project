package com.company.gamestore.repository;

import com.company.gamestore.model.Console;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {
}
