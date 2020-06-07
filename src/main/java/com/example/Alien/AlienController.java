package com.example.Alien;

import java.util.ArrayList;
import java.util.UUID;

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

    public ArrayList<Alien> globalList = new ArrayList<>();

    @GetMapping(path = "/alien")
    public ResponseEntity<ArrayList<Alien>> getAllAliens() {
        return new ResponseEntity<ArrayList<Alien>>(globalList, HttpStatus.OK);
    }

    @PostMapping(path = "/alien", consumes = { "application/json" })
    public ResponseEntity<Alien> addAlien(@RequestBody Alien alien) {
        System.out.println("here");
        Alien _alien = new Alien();
        _alien.setId(UUID.randomUUID().toString().replace("-", ""));
        _alien.setAname(alien.getAname());
        _alien.setTech(alien.getTech());
        globalList.add(_alien);
        return new ResponseEntity<Alien>(_alien, HttpStatus.OK);
    }

    @PutMapping(path = "/alien/{id}", consumes = { "application/json" })
    public ResponseEntity<Alien> updateAlien(@RequestBody Alien alien, @PathVariable("id") String id) {
        for (int i = 0; i < globalList.size(); i++) {
            Alien item = globalList.get(i);
            if (item.getId().equals(id)) {
                globalList.get(i).setAname(alien.getAname());
                globalList.get(i).setTech(alien.getTech());
            }
        }
        return new ResponseEntity<Alien>(alien, HttpStatus.OK);
    }

    @DeleteMapping(path = "/alien/{id}", consumes = { "application/json" })
    public ResponseEntity<Alien> deleteAlien(@RequestBody Alien alien, @PathVariable("id") String id) {
        for (int i = 0; i < globalList.size(); i++) {
            Alien item = globalList.get(i);
            if (item.getId().equals(id)) {
                globalList.remove(i);
            }
        }
        return new ResponseEntity<Alien>(HttpStatus.OK);
    }

}