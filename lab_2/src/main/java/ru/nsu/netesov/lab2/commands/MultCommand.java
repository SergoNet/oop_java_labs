package ru.nsu.netesov.lab2.commands;


import org.apache.log4j.Logger;
import ru.nsu.netesov.lab2.Context;

import java.util.List;

public class MultCommand implements Command{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void execute(Context context, List<String> args) {
        Double number1 = context.pop();
        Double number2 = context.pop();
        context.push(number1 * number2);
        logger.info(args.get(0) +  " command finishes work with result: " + context.peek());
    }
}
