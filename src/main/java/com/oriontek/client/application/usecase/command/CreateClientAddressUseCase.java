package com.oriontek.client.application.usecase.command;

import com.oriontek.client.shared.exception.ClientNotFoundException;
import com.oriontek.client.domain.model.Address;
import com.oriontek.client.domain.model.Client;
import com.oriontek.client.infraestructure.repository.ClientAddressRepository;
import com.oriontek.client.infraestructure.repository.ClientRepository;
import org.springframework.stereotype.Component;

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
