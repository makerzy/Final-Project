package com.company.gamestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.gamestore.model.*;

import java.util.List;

public interface ConsoleRepository extends JpaRepository<Console, Integer> {

    List<Console> findByManufacturer(String manufacturer);
}
