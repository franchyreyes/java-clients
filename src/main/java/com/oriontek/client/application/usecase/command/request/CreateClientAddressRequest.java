package com.oriontek.client.application.usecase.command.request;

public class CreateClientAddressRequest {

    private Long clientId;
    private String description;

    public CreateClientAddressRequest(){}

    public CreateClientAddressRequest(Long clientId, String description) {
        this.clientId = clientId;
        this.description = description;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

