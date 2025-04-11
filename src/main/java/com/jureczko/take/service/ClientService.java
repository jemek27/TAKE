package com.jureczko.take.service;

import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Client;
import com.jureczko.take.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client with ID " + id + " not found"));
    }

    public Client saveClient(Client client) {
        boolean emailTaken = client.getId() == null
                ? clientRepository.existsByEmail(client.getEmail())
                : clientRepository.existsByEmailAndIdNot(client.getEmail(), client.getId());

        if (emailTaken) {
            throw new DataIntegrityViolationException("Email is already taken.");
        }
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Client client = getClientById(id);
        clientRepository.delete(client);
    }

    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }
}
