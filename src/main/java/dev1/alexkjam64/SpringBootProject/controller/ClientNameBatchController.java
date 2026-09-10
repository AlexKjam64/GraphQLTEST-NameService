package dev1.alexkjam64.SpringBootProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev1.alexkjam64.SpringBootProject.service.ClientNameService;

@RestController
@RequestMapping("/batch")
public class ClientNameBatchController {
    private final ClientNameService clientNameService;

    public ClientNameBatchController(ClientNameService clientNameService){
        this.clientNameService = clientNameService;
    }

    @PostMapping
    public ResponseEntity<?> batchName(@RequestBody List<Integer> ids){
        return ResponseEntity.ok(clientNameService.retrieveAllNames(ids));
    }
}