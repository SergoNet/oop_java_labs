package ru.nsu.netesov.lab5.utility;

public class ErrorMessage extends Message {
    private final String exception;
    public ErrorMessage(String exception) {
        super("error");
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }
}
