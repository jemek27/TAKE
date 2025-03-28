package com.jureczko.take.controller;

import com.jureczko.take.model.Client;
import com.jureczko.take.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) { this.clientService = clientService; }

    @GetMapping
    public List<Client> getAllClients() { return clientService.getAllClients(); }

    @PostMapping
    public Client createClient(@RequestBody Client client) { return clientService.saveClient(client); }
}
