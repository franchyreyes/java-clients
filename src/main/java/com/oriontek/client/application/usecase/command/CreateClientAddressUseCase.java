package com.oriontek.client.application.usecase.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.request.CreateClientAddressRequest;
import com.oriontek.client.dto.ClientAddressDTO;
import com.oriontek.client.exception.ClientNotFoundException;
import com.oriontek.client.model.Address;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.ClientAddressRepository;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateClientAddressUseCase {

    private final ClientRepository clientRepository;
    private final ClientAddressRepository clientAddressRepository;

    public CreateClientAddressUseCase(ClientRepository clientRepository,
                                              ClientAddressRepository clientAddressRepository) {
        this.clientRepository = clientRepository;
        this.clientAddressRepository = clientAddressRepository;
    }

    public Address execute(Long clientId, String description) {
        // Validar existencia del cliente
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        // Crear nueva dirección
        Address address = new Address();
        address.setClient(client);
        address.setDescription(description);

        // Guardar en repositorio
        return clientAddressRepository.save(address);
    }
}
