package org.example.inventoryservice.response;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private T data;
    private String message;
    private int status;

    public ApiResponse(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status.value();
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
