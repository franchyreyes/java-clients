package com.oriontek.client.application.handler;

public interface Handler<C, R> {
    R handle(C commandOrQuery);
}
