package ru.nsu.netesov.lab5.utility.client;

import ru.nsu.netesov.lab5.utility.Message;

public class LogoutToTheSystem extends Message {
    private String ID;
    public LogoutToTheSystem(String ID) {
        super("logout");
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
}
