package com.oriontek.client.application.handler.query;

import com.oriontek.client.application.handler.Handler;
import com.oriontek.client.application.usecase.query.GetClientAddressUseCase;
import com.oriontek.client.dto.ClientAddressDTO;
import com.oriontek.client.exception.ClientAddressNotFoundException;
import com.oriontek.client.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetClientAddressHandler implements Handler<Long, List<ClientAddressDTO>> {

    private final GetClientAddressUseCase getClientAddressUseCase;

    public GetClientAddressHandler(GetClientAddressUseCase getClientAddressUseCase) {
        this.getClientAddressUseCase = getClientAddressUseCase;
    }

    @Override
    public List<ClientAddressDTO> handle(Long clientId) {
        List<Address> addresses = getClientAddressUseCase.execute(clientId);

        if (addresses.isEmpty()) {
            throw new ClientAddressNotFoundException(clientId);
        }

        return addresses.stream()
                .map(address -> new ClientAddressDTO(
                        address.getId(),
                        address.getDescription()
                ))
                .toList();
    }
}
