package com.oriontek.client.dto;

import com.oriontek.client.model.Address;
import com.oriontek.client.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name;
    private List<String> addresses;

    // Constructor que transforma la entidad Client en un DTO
    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.addresses = client.getAddresses() != null
                ? client.getAddresses().stream()
                .map(Address::getDescription)
                .collect(Collectors.toList())
                : null;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public List<String> getAddresses() { return addresses; }
}

