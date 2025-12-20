package com.india.railway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public  class ApiResponseFactory {

    private ApiResponseFactory() {}

    public <T> ResponseEntity<ApiResponse<T>> success(
            T data, String message, HttpStatus status) {

        ApiResponse<T> response = new ApiResponse<>(
                true,
                message,
                data,
                null,
                status.value(),
                null
        );

        return ResponseEntity.status(status).body(response);
    }

    public <T> ResponseEntity<ApiResponse<T>> validationError(
            List<String> errors) {

        ApiResponse<T> response = new ApiResponse<>(
                false,
                "Validation Failed",
                null,
                errors,
                HttpStatus.BAD_REQUEST.value(),
                null
        );

        return ResponseEntity.badRequest().body(response);
    }

    public <T> ResponseEntity<ApiResponse<T>> error(
            String message, HttpStatus status) {

        ApiResponse<T> response = new ApiResponse<>(
                false,
                message,
                null,
                null,
                status.value(),
                null
        );

        return ResponseEntity.status(status).body(response);
    }
}

