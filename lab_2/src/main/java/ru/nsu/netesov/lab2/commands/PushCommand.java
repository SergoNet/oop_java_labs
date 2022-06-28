package ru.nsu.netesov.lab2.commands;


import org.apache.log4j.Logger;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.exceptions.IncorrectAmountOfArgsException;

import java.util.List;

public class PushCommand implements Command {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        validate(args);
        double number;
        try {
            number = Double.parseDouble(args.get(1));
        } catch (NumberFormatException e) {
            number = context.getValueByKey(args.get(1));
        }
        context.push(number);
        logger.info(args.get(0) +  " command finishes work with context: " + context);
    }

    private void validate(List<String> args) {
        if (args.size() != 2) {
            throw new IncorrectAmountOfArgsException("push command needs 1 arg");
        }
    }
}

