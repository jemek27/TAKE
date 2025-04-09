package com.jureczko.take.controller;

import com.jureczko.take.dto.client.*;
import com.jureczko.take.mapper.ClientMapper;
import com.jureczko.take.model.Client;
import com.jureczko.take.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientRequest clientRequest) {
        try {
            Client client = clientMapper.toEntity(clientRequest);
            Client savedClient = clientService.saveClient(client);
            return ResponseEntity.ok(clientMapper.toDto(savedClient));
        } catch (DataIntegrityViolationException e) {
            // Obsługa sytuacji, gdy email jest już zajęty
            return ResponseEntity.badRequest().body("Email jest już zajęty.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(
                clientService.getAllClients().stream()
                        .map(clientMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(clientMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        try {
            Client client = clientMapper.toEntity(clientRequest);
            client.setId(id);
            Client updatedClient = clientService.updateClient(id, client);
            return ResponseEntity.ok(clientMapper.toDto(updatedClient));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email jest już zajęty.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
