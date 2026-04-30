package com.oriontek.client.application.handler.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.DeleteClientUseCase;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientHandle implements Handler<Long, Void> {

    private final DeleteClientUseCase deleteClientUseCase;

    public DeleteClientHandle(DeleteClientUseCase deleteClientUseCase) {
        this.deleteClientUseCase = deleteClientUseCase;
    }

    @Override
    public Void handle(Long clientId) {
        deleteClientUseCase.execute(clientId);
        return null; // No se devuelve nada en un delete
    }
}
