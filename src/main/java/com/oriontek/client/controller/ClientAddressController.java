package com.oriontek.client.controller;

import com.oriontek.client.application.handler.command.CreateClientAddressHandle;
import com.oriontek.client.application.handler.query.GetClientAddressHandler;
import com.oriontek.client.application.usecase.command.request.CreateClientAddressRequest;
import com.oriontek.client.dto.ClientAddressDTO;
import com.oriontek.client.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientAddressController {

    private final GetClientAddressHandler getClientAddressHandler;
    private final CreateClientAddressHandle createClientAddressHandle;

    public ClientAddressController(GetClientAddressHandler getClientAddressHandler, CreateClientAddressHandle createClientAddressHandle) {
        this.getClientAddressHandler = getClientAddressHandler;
        this.createClientAddressHandle = createClientAddressHandle;
    }

    // Command side: create or update client address
    @PostMapping("{id}/address")
    public ResponseEntity<SuccessResponse<ClientAddressDTO>> createOrUpdateAddress(
            @PathVariable Long id,
            @RequestBody CreateClientAddressRequest command) {

        ClientAddressDTO address = createClientAddressHandle.handle(command);
        SuccessResponse<ClientAddressDTO> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Client address saved successfully",
                address
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // Query side: get client address by client id
    @GetMapping("{id}/address")
    public ResponseEntity<SuccessResponse<List<ClientAddressDTO>>> getAddressByClientId(@PathVariable Long id) {
        List<ClientAddressDTO> addresses = getClientAddressHandler.handle(id);
        SuccessResponse<List<ClientAddressDTO>> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client address retrieved successfully",
                addresses
        );
        return ResponseEntity.ok(response);
    }
}
