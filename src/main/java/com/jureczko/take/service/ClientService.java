package com.jureczko.take.service;

import com.jureczko.take.model.Client;
import com.jureczko.take.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client updateClient(Long id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found");
        }


        if (clientRepository.existsByEmailAndIdNot(client.getEmail(), id)) {
            throw new DataIntegrityViolationException("Email jest już zajęty.");
        }

        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
