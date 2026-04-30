package com.oriontek.client.application.usecase.command;

import com.oriontek.client.exception.ClientNotFoundException;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientUseCase {

    private final ClientRepository clientRepository;

    public DeleteClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void execute(Long clientId) {
        // Validar existencia del cliente
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }

        // Eliminar cliente
        clientRepository.deleteById(clientId);
    }
}

