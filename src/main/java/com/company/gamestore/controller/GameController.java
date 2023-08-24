package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    GameRepository GameRepo;

    //create new game


    //read games


    //update game


    //delete game


    //search games by studio
    @GetMapping("/game/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return GameRepo.findByStudio(studio);
    }


    //search games by ESRB rating
    @GetMapping("/game/rating/{esrb_rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByEsrb(@PathVariable String esrb_rating) {
        return GameRepo.findByEsrb_rating(esrb_rating);
    }


    //search games by title
    @GetMapping("/game/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByTitle(@PathVariable String title) {
        return GameRepo.findByTitle(title);
    }
}
