package com.oriontek.client.application.handler.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.UpdateClientUseCase;
import com.oriontek.client.application.usecase.command.request.UpdateClientRequest;
import com.oriontek.client.application.dto.ClientDTO;
import com.oriontek.client.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class UpdateClientHandler implements Handler<UpdateClientRequest, ClientDTO> {

    private final UpdateClientUseCase updateClientUseCase;

    public UpdateClientHandler(UpdateClientUseCase updateClientUseCase) {
        this.updateClientUseCase = updateClientUseCase;
    }

    @Override
    public ClientDTO handle(UpdateClientRequest command) {
        Client updated = updateClientUseCase.execute(
                command.getClientId(),
                command.getName()
        );

        return new ClientDTO(updated);
    }
}