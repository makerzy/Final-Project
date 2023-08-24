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
    ConsoleRepository ConsoleRepo;



    //create console
    @PostMapping(value = "/console")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console console) {
        return ConsoleRepo.save(console);
    }


    //read console
    @GetMapping("/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> console = ConsoleRepo.findById(id);
        if (console.isPresent())
            return console.get();
        return null;
    }




    // update console
    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody Console console, @PathVariable Integer id) {
        Optional<Console> console1 = ConsoleRepo.findById(id);
        if (console1.isPresent())
            ConsoleRepo.save(console);

    }


    //delete console
    @DeleteMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        ConsoleRepo.deleteById(id);
    }


    //search  console by manufacturer
    @GetMapping("/console/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return ConsoleRepo.findByManufacturer(manufacturer);
    }
}
