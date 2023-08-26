package com.company.gamestore.repository;


import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByEsrbRating(String esrbRating);

    List<Game> findByStudio(String studio);

    List<Game> findByTitle(String title);
}
