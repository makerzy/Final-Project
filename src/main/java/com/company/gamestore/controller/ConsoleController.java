package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository ConRepo;


    // read console
    @GetMapping("/console")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {
        return ConRepo.findAll();
    }



    // update console
    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console, @PathVariable Integer id) {
        Optional<Console> console1 = ConRepo.findById(id);
        if (console1.isPresent())
            ConRepo.save(console);

    }



    //  create console
    @PostMapping(value = "/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return ConRepo.save(console);
    }



    // delete console
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        ConRepo.deleteById(id);
    }




    // search console by manufacturer

    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return ConRepo.findByManufacturer(manufacturer);
    }

    }
