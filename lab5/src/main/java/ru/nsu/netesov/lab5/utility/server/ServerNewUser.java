package ru.nsu.netesov.lab5.utility.server;

import ru.nsu.netesov.lab5.utility.Message;

public class ServerNewUser extends Message {
    private String name;
    public ServerNewUser(String name){
        super("userlogin");
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
