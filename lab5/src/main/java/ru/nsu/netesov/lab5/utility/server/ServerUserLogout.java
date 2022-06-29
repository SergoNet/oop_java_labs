package ru.nsu.netesov.lab5.utility.server;

import ru.nsu.netesov.lab5.utility.Message;

public class ServerUserLogout extends Message {
    private String name;
    public ServerUserLogout(String name) {
        super("userlogout");
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
