package com.oriontek.client.controller;

import com.oriontek.client.application.handler.command.CreateClientAddressHandle;
import com.oriontek.client.application.handler.command.DeleteClientAddressHandler;
import com.oriontek.client.application.handler.query.GetClientAddressHandler;
import com.oriontek.client.application.usecase.command.request.CreateClientAddressRequest;
import com.oriontek.client.application.usecase.command.request.DeleteClientAddressRequest;
import com.oriontek.client.dto.ClientAddressDTO;
import com.oriontek.client.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@Tag(name = "Client Addresses", description = "Gestión de direcciones de clientes")
public class ClientAddressController {

    private final GetClientAddressHandler getClientAddressHandler;
    private final CreateClientAddressHandle createClientAddressHandle;
    private final DeleteClientAddressHandler deleteClientAddressHandler;

    public ClientAddressController(GetClientAddressHandler getClientAddressHandler, CreateClientAddressHandle createClientAddressHandle, DeleteClientAddressHandler deleteClientAddressHandler) {
        this.getClientAddressHandler = getClientAddressHandler;
        this.createClientAddressHandle = createClientAddressHandle;
        this.deleteClientAddressHandler = deleteClientAddressHandler;
    }

    // Command side: create or update client address
    @Operation(
            summary = "Crear o actualizar dirección de cliente",
            description = "Permite registrar una nueva dirección o actualizar una existente para un cliente específico."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dirección creada o actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PostMapping("{id}/address")
    public ResponseEntity<SuccessResponse<ClientAddressDTO>> createOrUpdateAddress(
            @Parameter(description = "ID único del cliente", required = true) @PathVariable Long id,
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
    @Operation(
            summary = "Obtener direcciones de cliente por ID",
            description = "Devuelve todas las direcciones asociadas a un cliente específico identificado por su ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Direcciones obtenidas exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("{id}/address")
    public ResponseEntity<SuccessResponse<List<ClientAddressDTO>>> getAddressByClientId(
            @Parameter(description = "ID único del cliente", required = true) @PathVariable Long id) {

        List<ClientAddressDTO> addresses = getClientAddressHandler.handle(id);
        SuccessResponse<List<ClientAddressDTO>> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Client address retrieved successfully",
                addresses
        );
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Eliminar dirección de cliente por ID",
            description = "Permite eliminar una dirección específica asociada a un cliente. "
                    + "El cliente permanece intacto, solo se elimina la dirección indicada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dirección eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cliente o dirección no encontrada")
    })
    @DeleteMapping("/{idClient}/address/{idAddress}")
    public ResponseEntity<SuccessResponse<Void>> deleteAddressById(
            @Parameter(description = "ID único del cliente", required = true) @PathVariable Long idClient,
            @Parameter(description = "ID único de la dirección", required = true) @PathVariable Long idAddress) {

        DeleteClientAddressRequest request = new DeleteClientAddressRequest(idClient, idAddress);
        deleteClientAddressHandler.handle(request);
        SuccessResponse<Void> response = new SuccessResponse<>(
                LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Address deleted successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
