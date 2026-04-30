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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clients", description = "Gestión de clientes")
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
    @Operation(
            summary = "Crear un nuevo cliente",
            description = "Permite registrar un nuevo cliente en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<ClientDTO>> createClient(
            @RequestBody CreateClientRequest command) {

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
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista paginada de clientes")
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
    @Operation(
            summary = "Obtener cliente por ID",
            description = "Devuelve la información de un cliente específico identificado por su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ClientDTO>> getClientById(
            @Parameter(description = "ID único del cliente", required = true)
            @PathVariable Long id) {

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
    @Operation(
            summary = "Actualizar cliente por ID",
            description = "Permite actualizar la información de un cliente existente identificado por su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<ClientDTO>> updateClientById(
            @Parameter(description = "ID único del cliente", required = true) @PathVariable Long id,
            @Parameter(description = "Datos para actualizar el cliente", required = true) @RequestBody UpdateClientRequest command) {

        ClientDTO client = updateClientHandler.handle(command);
        SuccessResponse<ClientDTO> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client updated successfully",
                client
        );
        return ResponseEntity.ok(response);
    }

    // Command side: Delete client
    @Operation(
            summary = "Eliminar cliente por ID",
            description = "Permite eliminar un cliente existente identificado por su ID. "
                    + "Al eliminar un cliente, también se eliminan todas sus direcciones asociadas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteClientById(
            @Parameter(description = "ID único del cliente", required = true) @PathVariable Long id) {

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
