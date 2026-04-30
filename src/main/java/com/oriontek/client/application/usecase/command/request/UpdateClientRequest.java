package com.oriontek.client.application.usecase.command.request;

public class UpdateClientRequest {

    private Long clientId;
    private String name;


    public UpdateClientRequest(Long clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


