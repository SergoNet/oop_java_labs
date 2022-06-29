package ru.nsu.netesov.lab5.utility;

public class Message {
    private final String message;
    public Message(String message) {
        this.message = message;
    }
    public String getType(){
        return message;
    }
}
