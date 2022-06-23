package ru.nsu.netesov.lab5.utility.server;

import ru.nsu.netesov.lab5.utility.Message;

public class ServerConfirm extends Message {
    private String ID;
    public ServerConfirm(String ID) {
        super("connection");
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
