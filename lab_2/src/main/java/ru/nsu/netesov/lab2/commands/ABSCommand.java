package ru.nsu.netesov.lab2.commands;


import org.apache.log4j.Logger;
import ru.nsu.netesov.lab2.Context;

import java.util.List;

public class ABSCommand implements Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        Double number = context.pop();
        context.push(Math.abs(number));
        logger.info(args.get(0) +  " command finishes work with result: " + context.peek());
    }
}
