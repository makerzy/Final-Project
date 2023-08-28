package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository GameRepo;


    // update game
    @PutMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game, @PathVariable Integer id) {
        Optional<Game> game1 = GameRepo.findById(id);
        if (game1.isPresent())
            GameRepo.save(game);

    }



    // create game

    @PostMapping(value = "/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {
        return GameRepo.save(game);
    }




    // read game
    @GetMapping("/games")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGame() {
        return GameRepo.findAll();
    }

    // read game by id
    @GetMapping("/games/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable int id) {
        Optional<Game> game = GameRepo.findById(id);
        if (game.isPresent())
            return game.get();
        return null;
    }





    // delete game
    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        GameRepo.deleteById(id);
    }




    // search game by studio
    @GetMapping("/games/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return GameRepo.findByStudio(studio);
    }


    // search game by esrb rating
    @GetMapping("/games/esrbRating/{esrbRating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByEsrbRating(@PathVariable String esrbRating) {
        return GameRepo.findByEsrbRating(esrbRating);
    }


    // search game by title
    @GetMapping("/games/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByTitle(@PathVariable String title) {
        return GameRepo.findByTitle(title);
    }

}