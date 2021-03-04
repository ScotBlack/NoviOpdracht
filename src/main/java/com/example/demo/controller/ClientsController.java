package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import org.apache.catalina.filters.ExpiresFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;



@RestController
public class ClientsController {

    Logger logger = LoggerFactory.getLogger(ClientsController.class);
    Map<Long, String> data = new HashMap<>();

    ClientsController() {
        this.data.put(1L, "Janssen");
        this.data.put(2L, "Piet");
        this.data.put(3L, "Kees");
    }


    @GetMapping ("/clients")
    public ResponseEntity<Object> getClients() {
        System.out.println("test4");
        return new ResponseEntity<Object>(data, HttpStatus.OK);
    }


    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getClients(@PathVariable("id")Long id) {
        if (!this.data.keySet().contains(id)) {
            throw new RecordNotFoundException();
        } else {
            return new ResponseEntity<Object>(this.data.get(id), HttpStatus.OK);
        }
    }


    @DeleteMapping("clients/{id}")
    public ResponseEntity<Object> deleteClients(@PathVariable("id")Long id) {
        this.data.remove(id);
        return new ResponseEntity<Object>("Record Deleted", HttpStatus.NO_CONTENT);
    }


    @PostMapping("clients")
    public ResponseEntity<Object> addClients(@RequestBody String clientName) {
        long maxID = this.data.keySet().stream().max(Comparator.comparing(Long::valueOf)).get();
        this.data.put(maxID + 1, clientName);
        return new ResponseEntity<Object> ("Record created", HttpStatus.OK);
    }


    @PutMapping("clients/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable("id")Long id, @RequestBody String clientName) {
        this.data.put(id, clientName);
        return new ResponseEntity<Object>("Record updated", HttpStatus.OK);
    }
}



