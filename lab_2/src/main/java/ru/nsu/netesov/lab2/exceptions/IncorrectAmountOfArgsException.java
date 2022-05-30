package ru.nsu.netesov.lab2.exceptions;

public class IncorrectAmountOfArgsException extends RuntimeException {
    public IncorrectAmountOfArgsException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }
    public IncorrectAmountOfArgsException(String errorMessage) {
        super(errorMessage);
    }
}
