package com.oriontek.client.application.usecase.command;

import com.oriontek.client.application.usecase.command.request.CreateClientRequest;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateClientUseCase {

    private final ClientRepository repository;
    public CreateClientUseCase(ClientRepository repository) { this.repository = repository; }

    public Client execute(CreateClientRequest command) {
        Client client = new Client(command.getName(), command.getAddresses());
        command.getAddresses().forEach(addr -> addr.setClient(client));
        return repository.save(client);
    }
}
