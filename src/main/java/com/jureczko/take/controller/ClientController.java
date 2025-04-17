package com.jureczko.take.controller;

import com.jureczko.take.dto.client.*;
import com.jureczko.take.dto.order.OrderResponse;
import com.jureczko.take.mapper.ClientMapper;
import com.jureczko.take.mapper.OrderMapper;
import com.jureczko.take.model.Client;
import com.jureczko.take.service.ClientService;
import com.jureczko.take.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final OrderService orderService;
    private final ClientMapper clientMapper;
    private final OrderMapper orderMapper;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(
                clientService.getAllClients().stream()
                        .map(ClientResponse::new)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(new ClientResponse(client));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponse>> getClientOrdersById(@PathVariable Long id) {
        return ResponseEntity.ok(
                orderService.getOrdersByClientId(id).stream()
                        .map(orderMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);
        Client savedClient = clientService.saveClient(client);
        return ResponseEntity.ok(new ClientResponse(savedClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody UpdateClientRequest updateRequest) {
        Client updatedClient = clientService.updateClient(id, updateRequest);
        return ResponseEntity.ok(new ClientResponse(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
