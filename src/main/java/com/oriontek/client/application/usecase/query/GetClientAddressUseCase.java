package com.oriontek.client.application.usecase.query;

import com.oriontek.client.model.Address;
import com.oriontek.client.repository.ClientAddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GetClientAddressUseCase {

    private final ClientAddressRepository clientAddressRepository;

    public GetClientAddressUseCase(ClientAddressRepository clientAddressRepository) {
        this.clientAddressRepository = clientAddressRepository;
    }

    public List<Address> execute(Long clientId) {
        return clientAddressRepository.findAllByClientId(clientId);
    }
}
