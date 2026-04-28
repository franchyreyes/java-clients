package com.oriontek.client.application.handler.query;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.query.GetClientUseCase;
import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.exception.ClientNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class GetClientHandler implements Handler<Long, ClientDTO> {

    private final GetClientUseCase getClientUseCase;

    public GetClientHandler(GetClientUseCase getClientUseCase) {
        this.getClientUseCase = getClientUseCase;
    }

    @Override
    public ClientDTO handle(Long id) {
        return getClientUseCase.execute(id)
                .map(ClientDTO::new)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }
}

