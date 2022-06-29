package ru.nsu.netesov.lab5.utility.server;

import ru.nsu.netesov.lab5.utility.Message;
import java.util.LinkedList;

public class ServerList extends Message {
    private final LinkedList<String> users;
    public ServerList(LinkedList<String> users) {
        super("list");
        this.users = users;
    }
    public LinkedList<String> getUsers() {
        return users;
    }
}
