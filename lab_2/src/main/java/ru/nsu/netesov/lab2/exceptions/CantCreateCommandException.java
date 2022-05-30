package ru.nsu.netesov.lab2.exceptions;

public class CantCreateCommandException extends RuntimeException {
    public CantCreateCommandException(String message) {
        super(message);
    }
    public CantCreateCommandException(String message, Throwable exception) {
        super(message,exception);
    }
}
