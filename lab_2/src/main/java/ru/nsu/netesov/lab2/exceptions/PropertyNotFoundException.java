package ru.nsu.netesov.lab2.exceptions;

public class PropertyNotFoundException extends RuntimeException {
    public PropertyNotFoundException(String message) {
        super(message);
    }
    public PropertyNotFoundException(String message, Throwable exception) {
        super(message,exception);
    }
}
