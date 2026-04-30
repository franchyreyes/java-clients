package com.oriontek.client.controller;

import com.oriontek.client.application.handler.command.CreateClientHandler;
import com.oriontek.client.application.handler.command.DeleteClientHandle;
import com.oriontek.client.application.handler.command.UpdateClientHandler;
import com.oriontek.client.application.handler.query.GetAllClientHandler;
import com.oriontek.client.application.handler.query.GetClientHandler;
import com.oriontek.client.application.usecase.command.request.CreateClientRequest;
import com.oriontek.client.application.usecase.command.request.UpdateClientRequest;
import com.oriontek.client.dto.ClientDTO;
import com.oriontek.client.response.SuccessResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final CreateClientHandler createClientHandler;
    private final GetClientHandler getClientHandler;
    private final UpdateClientHandler updateClientHandler;
    private final DeleteClientHandle deleteClientHandle;
    private final GetAllClientHandler getAllClientHandler;

    public ClientController(CreateClientHandler createClientHandler,
                            GetClientHandler getClientHandler,
                            UpdateClientHandler updateClientHandler,
                            DeleteClientHandle deleteClientHandle,
                            GetAllClientHandler getAllClientHandler

                            ) {
        this.createClientHandler = createClientHandler;
        this.getClientHandler = getClientHandler;
        this.updateClientHandler = updateClientHandler;
        this.deleteClientHandle = deleteClientHandle;
        this.getAllClientHandler = getAllClientHandler;
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

    // Query side: get All client
    @GetMapping
    public ResponseEntity<SuccessResponse<Page<ClientDTO>>> getAllClients(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClientDTO> clients = getAllClientHandler.handle(pageable);

        SuccessResponse<Page<ClientDTO>> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Clients retrieved successfully",
                clients
        );

        return ResponseEntity.ok(response);
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

    // Command side: update client by id
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<ClientDTO>> updateClientById(@PathVariable Long id,@RequestBody UpdateClientRequest command) {
        ClientDTO client = updateClientHandler.handle(command);
        SuccessResponse<ClientDTO> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client updated successfully",
                client
        );
        return ResponseEntity.ok(response);
    }

    // Command side: create client
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteClientById(@PathVariable Long id) {
        deleteClientHandle.handle(id);
        SuccessResponse<Void> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client deleted successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
