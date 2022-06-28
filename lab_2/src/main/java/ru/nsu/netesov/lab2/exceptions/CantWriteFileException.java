package ru.nsu.netesov.lab2.exceptions;


import java.io.IOException;

public class CantWriteFileException extends IOException {
    public CantWriteFileException(String errorMessage) {
        super(errorMessage);
    }
}
