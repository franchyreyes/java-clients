package com.oriontek.client.application.usecase.query;

import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.model.Client;
import com.oriontek.client.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
