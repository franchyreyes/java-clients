package com.oriontek.client.application.handler.command;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.command.CreateClientAddressUseCase;
import com.oriontek.client.application.usecase.command.request.CreateClientAddressRequest;
import com.oriontek.client.dto.ClientAddressDTO;
import com.oriontek.client.model.Address;
import org.springframework.stereotype.Component;

@Component
public class CreateClientAddressHandle implements Handler<CreateClientAddressRequest, ClientAddressDTO> {

    private final CreateClientAddressUseCase createClientAddressUseCase;

    public CreateClientAddressHandle(CreateClientAddressUseCase createClientAddressUseCase) {
        this.createClientAddressUseCase = createClientAddressUseCase;
    }

    @Override
    public ClientAddressDTO handle(CreateClientAddressRequest command) {
        Address saved = createClientAddressUseCase.execute(command.getClientId(), command.getDescription());
        return new ClientAddressDTO(saved.getDescription());
    }
}
