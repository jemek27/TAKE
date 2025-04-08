package com.jureczko.take.service;

import com.jureczko.take.model.Client;
import com.jureczko.take.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() { return clientRepository.findAll(); }

    public Client saveClient(Client client) { return clientRepository.save(client); }

    public Client findById(Long id) { return clientRepository.findById(id).orElseThrow(); }

    public void delete(Long id) { clientRepository.deleteById(id); }
}
