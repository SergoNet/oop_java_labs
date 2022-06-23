package ru.nsu.netesov.lab5.utility;

public class ListOfUsers extends Message {
    private String ID;
    public ListOfUsers(String ID) {
        super("getlist");
        this.ID = ID;
    }
    public String getID() {
        return ID;
    }
}
