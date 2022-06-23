package ru.nsu.netesov.lab5.utility.server;

import ru.nsu.netesov.lab5.utility.Message;

public class ServerMessage extends Message {
    private final String content;
    private final String name;
    public ServerMessage(String content, String name) {
        super("server_message");
        this.content = content;
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public String getName() {
        return name;
    }
}
