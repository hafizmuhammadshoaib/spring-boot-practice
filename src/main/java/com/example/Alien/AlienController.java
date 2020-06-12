package com.example.Alien;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienController {

    @Autowired
    private AlienRepository alienRepository;
    public ArrayList<Alien> globalList = new ArrayList<>();

    @GetMapping(path = "/alien")
    public ResponseEntity<ArrayList<Alien>> getAllAliens() {
        Iterable<Alien> alienIterable = alienRepository.findAll();
        ArrayList<Alien> _aliens = new ArrayList<>();
        alienIterable.forEach(_aliens::add);
        return new ResponseEntity<ArrayList<Alien>>(_aliens, HttpStatus.OK);
    }

    @PostMapping(path = "/alien", consumes = {"application/json"})
    public ResponseEntity<Alien> addAlien(@RequestBody Alien alien) {
        alienRepository.save(alien);

        return new ResponseEntity<Alien>(alien, HttpStatus.OK);
    }

    @PutMapping(path = "/alien/{id}", consumes = {"application/json"})
    public ResponseEntity<Alien> updateAlien(@RequestBody Alien alien, @PathVariable("id") int id) {
        alienRepository.save(alien);
        return new ResponseEntity<Alien>(alien, HttpStatus.OK);
    }

    @DeleteMapping(path = "/alien/{id}", consumes = {"application/json"})
    public ResponseEntity<Alien> deleteAlien(@PathVariable("id") int id) {
//
        alienRepository.deleteById(id);
        return new ResponseEntity<Alien>(HttpStatus.OK);
    }

}