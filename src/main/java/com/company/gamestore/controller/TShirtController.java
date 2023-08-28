package com.company.gamestore.controller;


import com.company.gamestore.model.TShirt;
import com.company.gamestore.repository.TShirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TShirtController {

    @Autowired
    TShirtRepository tShirtRepository;

    @GetMapping("/tshirts")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirts(){
        return tShirtRepository.findAll();
    }

    @GetMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt getTShirtById(@PathVariable int id){
        return tShirtRepository.findById(id).orElse(null);
    }

    @GetMapping("/tshirts/colors/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color){
        return tShirtRepository.findByColor(color);
    }

    @GetMapping("/tshirts/sizes/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size){
        return tShirtRepository.findBySize(size);
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody TShirt tShirt){
        return tShirtRepository.save(tShirt);
    }

    @PutMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody TShirt tShirt, @PathVariable int id){
        Optional<TShirt> tShirt1 = tShirtRepository.findById(id);
        if(tShirt1.isPresent())
            tShirtRepository.save(tShirt);
    }

    @DeleteMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id){
        tShirtRepository.deleteById(id);
    }

}
