package com.jureczko.take.controller;

import com.jureczko.take.model.Client;
import com.jureczko.take.dto.ClientRequest;
import com.jureczko.take.dto.ClientDto;
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

    @GetMapping("/{id}")
    public Client get(@PathVariable Long id) { return clientService.findById(id); }

    @PostMapping
    public Client createClient(@RequestBody ClientRequest client) {
        Client c = new Client();
        c.setName(client.getName());
        c.setLastName(client.getLastName());
        c.setEmail(client.getEmail());
        c.setBirthday(client.getBirthday());
        c.setPhoneNumber(client.getPhoneNumber());
        return clientService.saveClient(c); }


    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client c) {
        c.setId(id);
        return clientService.saveClient(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { clientService.delete(id); }
}
