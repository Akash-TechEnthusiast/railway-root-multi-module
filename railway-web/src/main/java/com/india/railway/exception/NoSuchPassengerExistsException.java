package com.india.railway.exception;

public class NoSuchPassengerExistsException extends RuntimeException {

    private String message;

    public NoSuchPassengerExistsException() {
    }

    public NoSuchPassengerExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
