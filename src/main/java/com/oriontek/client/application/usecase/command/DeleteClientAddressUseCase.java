package com.oriontek.client.application.usecase.command;

import com.oriontek.client.shared.exception.ClientAddressNotFoundException;
import com.oriontek.client.shared.exception.ClientNotFoundException;
import com.oriontek.client.infraestructure.repository.ClientAddressRepository;
import com.oriontek.client.infraestructure.repository.ClientRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientAddressUseCase {

    private final ClientAddressRepository addressRepository;
    private final ClientRepository clientRepository;

    public DeleteClientAddressUseCase(
            ClientAddressRepository addressRepository,ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public void execute(Long clientId, Long addressId) {

        // Validate Client
        if (!clientRepository.existsById(clientId)) {
            throw new ClientNotFoundException(clientId);
        }

        // Validar existencia de la dirección
        if (!addressRepository.existsById(addressId)) {
            throw new ClientAddressNotFoundException(addressId);
        }

        // Delete Address
        addressRepository.deleteById(addressId);
    }
}

