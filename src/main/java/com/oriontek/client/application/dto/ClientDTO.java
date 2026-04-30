package com.oriontek.client.application.dto;

import com.oriontek.client.domain.model.Address;
import com.oriontek.client.domain.model.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name;
    private List<String> addresses;

    public ClientDTO(){}
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}

