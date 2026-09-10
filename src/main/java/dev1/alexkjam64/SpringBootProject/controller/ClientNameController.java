package dev1.alexkjam64.SpringBootProject.controller;

import dev1.alexkjam64.SpringBootProject.service.InvalidDataException;
import dev1.alexkjam64.SpringBootProject.service.NoDataException;
import dev1.alexkjam64.SpringBootProject.service.ClientNameService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DuplicateKeyException;

import dev1.alexkjam64.SpringBootProject.repository.ClientName;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ClientNameController extends Exception{
    private final ClientNameService clientService;

    public ClientNameController(ClientNameService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfo(@PathVariable int id){
        try{
            var nameVal = clientService.retrieve(id);
            return ResponseEntity.ok(nameVal);
        }catch(NoDataException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> postInfo(@RequestBody ClientName request, @PathVariable int id){
        try{
            clientService.create(request, id);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(InvalidDataException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(DuplicateKeyException e){
            return new ResponseEntity<>("User ID already has name information!", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putInfo(@RequestBody ClientName entity, @PathVariable int id) {
        try{
            clientService.update(entity, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(InvalidDataException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch(NoDataException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInfo(@PathVariable int id){
        try{
            clientService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoDataException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}