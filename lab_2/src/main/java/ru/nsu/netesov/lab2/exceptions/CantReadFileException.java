package ru.nsu.netesov.lab2.exceptions;


import java.io.IOException;

public class CantReadFileException extends IOException {
    public CantReadFileException(String errorMessage) {
        super(errorMessage);
    }
}
