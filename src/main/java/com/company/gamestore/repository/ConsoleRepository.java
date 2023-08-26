package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findByManufacturer(String manufacturer);
}
