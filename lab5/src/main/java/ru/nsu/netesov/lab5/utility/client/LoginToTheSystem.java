package ru.nsu.netesov.lab5.utility.client;

import ru.nsu.netesov.lab5.utility.Message;

public class LoginToTheSystem extends Message {
    private String name;
    public LoginToTheSystem(String name){
        super("login");
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
