package com.oriontek.client.application.usecase.query;

import com.oriontek.client.model.Client;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetClientUseCase {

    private final ClientRepository clientRepository;

    public GetClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<Client> execute(Long id) {
        return clientRepository.findById(id);
    }
}
