package com.oriontek.client.application.usecase.command.request;

import com.oriontek.client.domain.model.Address;

import java.util.List;

public class CreateClientRequest {
    private String name;
    private List<Address> addresses;

    public CreateClientRequest(){}
    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }
}
