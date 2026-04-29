package com.oriontek.client.dto;

public class ClientAddressDTO {

    private String description;

    public ClientAddressDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

