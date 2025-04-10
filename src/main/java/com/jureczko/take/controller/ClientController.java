package com.jureczko.take.controller;

import com.jureczko.take.dto.client.*;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.mapper.ClientMapper;
import com.jureczko.take.model.Client;
import com.jureczko.take.service.ClientService;
import jakarta.validation.Valid;
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
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(clientMapper.toDto(client));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.ok(clientMapper.toDto(savedClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        if (!clientService.existsById(id)) {
            throw new ResourceNotFoundException("Client with ID " + id + " not found");
        }
        Client client = clientMapper.toEntity(clientRequest);
        client.setId(id);
        Client updatedClient = clientService.saveClient(client);
        return ResponseEntity.ok(clientMapper.toDto(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
