package ru.nsu.netesov.lab2.exceptions;


public class ThereIsNoSuchKeyException extends RuntimeException{
    public ThereIsNoSuchKeyException(String errorMessage) {
        super(errorMessage);
    }
}
