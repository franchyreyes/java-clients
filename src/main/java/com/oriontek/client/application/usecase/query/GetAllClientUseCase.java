package com.oriontek.client.application.usecase.query;

import com.oriontek.client.application.dto.ClientDTO;
import com.oriontek.client.infraestructure.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class GetAllClientUseCase {
    private final ClientRepository clientRepository;

    public GetAllClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<ClientDTO> execute(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(ClientDTO::new);
    }
}
