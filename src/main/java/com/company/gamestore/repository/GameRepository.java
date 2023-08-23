package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

    // search game by studio
    List<Game> findByStudio(String studio);



    // search game by ersb_rating
    List<Game> findByEsrb_rating(String esrb_rating);



    // search game by title
    List<Game> findByTitle(String title);


}
