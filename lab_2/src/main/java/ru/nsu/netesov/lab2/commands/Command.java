package ru.nsu.netesov.lab2.commands;


import ru.nsu.netesov.lab2.Context;

import java.util.List;

public interface Command {
    void execute(Context context, List<String> args);
}

