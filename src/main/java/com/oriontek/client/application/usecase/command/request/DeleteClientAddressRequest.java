package com.oriontek.client.application.usecase.command.request;

public class DeleteClientAddressRequest {

    private Long clientId;
    private Long addresId;


    public DeleteClientAddressRequest(Long clientId, Long addresId) {
        this.clientId = clientId;
        this.addresId = addresId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getAddresId() {
        return addresId;
    }

    public void setAddresId(Long addresId) {
        this.addresId = addresId;
    }
}


