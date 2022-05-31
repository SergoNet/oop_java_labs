package ru.nsu.netesov.lab2.exceptions;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException(String errorMessage) {
        super(errorMessage);
    }
}
