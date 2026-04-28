package com.oriontek.client.controller;

import com.oriontek.client.application.handler.command.CreateClientHandler;
import com.oriontek.client.application.handler.query.GetClientHandler;
import com.oriontek.client.application.usecase.command.request.CreateClientRequest;
import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final CreateClientHandler createClientHandler;
    private final GetClientHandler getClientHandler;

    public ClientController(CreateClientHandler createClientHandler,
                            GetClientHandler getClientHandler) {
        this.createClientHandler = createClientHandler;
        this.getClientHandler = getClientHandler;
    }

    // Command side: create client
    @PostMapping
    public ResponseEntity<SuccessResponse<ClientDTO>> createClient(@RequestBody CreateClientRequest command) {
        ClientDTO client = createClientHandler.handle(command);
        SuccessResponse<ClientDTO> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Client created successfully",
                client
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Query side: get client by id
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ClientDTO>> getClientById(@PathVariable Long id) {
        ClientDTO client = getClientHandler.handle(id);
        SuccessResponse<ClientDTO> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client retrieved successfully",
                client
        );
        return ResponseEntity.ok(response);
    }
}
