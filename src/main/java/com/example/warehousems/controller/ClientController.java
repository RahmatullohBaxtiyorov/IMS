package com.example.warehousems.controller;

import com.example.warehousems.entity.Client;
import com.example.warehousems.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
@CrossOrigin
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public HttpEntity<?> getClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getClientById(@PathVariable UUID id) {
        return ResponseEntity.ok(clientRepository.findById(id));
    }

    @PostMapping
    public HttpEntity<?> addClient(@RequestBody Client client) {
        Client newclient = new Client();
        newclient.setName(client.getName());
        return ResponseEntity.ok(clientRepository.save(newclient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editClient(@PathVariable UUID id, @RequestBody Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) return (ResponseEntity<?>) ResponseEntity.badRequest();
        Client newclient = new Client();
        newclient.setName(client.getName());
        return ResponseEntity.ok(clientRepository.save(newclient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delteClient(@PathVariable UUID id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) return ResponseEntity.badRequest().build();
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
