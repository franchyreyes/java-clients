package com.oriontek.client.application.handler.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.DeleteClientAddressUseCase;
import com.oriontek.client.application.usecase.command.request.DeleteClientAddressRequest;
import org.springframework.stereotype.Component;

@Component
public class DeleteClientAddressHandler implements Handler<DeleteClientAddressRequest, Void> {

    private final DeleteClientAddressUseCase deleteClientAddressUseCase;

    public DeleteClientAddressHandler(DeleteClientAddressUseCase deleteClientAddressUseCase) {
        this.deleteClientAddressUseCase = deleteClientAddressUseCase;
    }

    @Override
    public Void handle(DeleteClientAddressRequest deleteClientAddressRequest) {
        deleteClientAddressUseCase.execute(deleteClientAddressRequest.getClientId(),deleteClientAddressRequest.getAddresId());
        return null; // Empty delete
    }
}

