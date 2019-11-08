package com.codegym.controller;

import com.codegym.model.Publisher;
import com.codegym.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class ApiPublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/api/publisher")
    public ResponseEntity<List<Publisher>> getPublisherList(){
        List<Publisher> publishers = (List<Publisher>) publisherService.findAll();

        if(publishers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(publishers,HttpStatus.OK);
    }

    @GetMapping("/api/publisher/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable Long id){
        Publisher publisher = publisherService.findById(id);

        if(publisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(publisher,HttpStatus.OK);
    }

    @PostMapping("/api/publisher")
    public ResponseEntity<Void> createPublisher(@RequestBody Publisher publisher) {
        publisherService.save(publisher);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/api/publisher/{id}")
    public ResponseEntity<Publisher> editPublisher(@RequestBody Publisher publisher, @PathVariable Long id){
        Publisher publisher1 = publisherService.findById(id);
        if(publisher1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        publisher1.setPublisher(publisher.getPublisher());
        publisherService.save(publisher1);

        return new ResponseEntity<>(publisher1,HttpStatus.OK);
    }

    @DeleteMapping("/api/publisher/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
        Publisher publisher = publisherService.findById(id);

        if(publisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        publisherService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

