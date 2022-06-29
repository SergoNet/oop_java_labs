package ru.nsu.netesov.lab5.utility.client;

import ru.nsu.netesov.lab5.utility.Message;

public class ClientMessage extends Message {
    private final String content;
    private final String ID;

    public ClientMessage(String content, String ID) {
        super("client_message");
        this.content = content;
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }
    public String getID() {
        return ID;
    }
}
