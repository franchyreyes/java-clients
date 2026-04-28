package com.oriontek.client.application.handler.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.CreateClientUseCase;
import com.oriontek.client.application.usecase.command.request.CreateClientRequest;
import com.oriontek.client.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateClientHandler implements Handler<CreateClientRequest,ClientDTO> {


    private final CreateClientUseCase createClientUseCase;

    public CreateClientHandler(CreateClientUseCase createClientUseCase) {
        this.createClientUseCase = createClientUseCase;
    }

    @Override
    public ClientDTO handle(CreateClientRequest command) {
        return new ClientDTO(createClientUseCase.execute(command));
    }
}
