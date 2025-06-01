package org.example.productservice.response;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private T data;
    private String message;
    private HttpStatus status;

    public ApiResponse(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = HttpStatus.valueOf(status.value());
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
