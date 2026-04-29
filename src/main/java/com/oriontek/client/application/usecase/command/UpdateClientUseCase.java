package com.oriontek.client.application.usecase.command;

import com.oriontek.client.exception.ClientNotFoundException;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public UpdateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Long clientId, String name) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        // Actualizar campos
        client.setName(name);

        // Guardar cambios
        return clientRepository.save(client);
    }
}

