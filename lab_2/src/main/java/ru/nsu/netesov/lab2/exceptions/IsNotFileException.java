package ru.nsu.netesov.lab2.exceptions;

import java.io.IOException;

public class IsNotFileException extends IOException {
    public IsNotFileException(String errorMessage, Throwable err) {
        super(errorMessage,err);
    }
    public IsNotFileException(String errorMessage) {
        super(errorMessage);
    }
}
