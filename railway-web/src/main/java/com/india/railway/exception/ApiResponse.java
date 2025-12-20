package com.india.railway.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    private int status;
    private String timestamp;
    private String path;




    public ApiResponse(boolean success, String message, T data,
                       List<String> errors, int status, String path) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.status = status;
        this.timestamp = java.time.Instant.now().toString();
        this.path = path;
    }

    // getters & setters
}
