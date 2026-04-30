package com.oriontek.client.shared.exception;


public class ClientAddressNotFoundException extends RuntimeException {
    public ClientAddressNotFoundException(Long id) {
        super("No addresses found for client id:" + id);
    }
}
