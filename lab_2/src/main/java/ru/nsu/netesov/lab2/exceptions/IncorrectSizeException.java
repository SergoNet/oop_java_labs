package ru.nsu.netesov.lab2.exceptions;

public class IncorrectSizeException extends RuntimeException {
    public IncorrectSizeException(String errorMessage) {
        super(errorMessage);
    }
}
