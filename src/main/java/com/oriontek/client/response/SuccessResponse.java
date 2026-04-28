package com.oriontek.client.response;

import java.time.LocalDateTime;

public class SuccessResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public SuccessResponse(LocalDateTime timestamp, int status, String message, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

