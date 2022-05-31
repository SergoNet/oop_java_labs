package ru.nsu.netesov.lab2;

import ru.nsu.netesov.lab2.commands.Command;
import org.apache.log4j.Logger;


public class Calculator {
    private final Logger logger = Logger.getLogger(this.getClass());

    private final Parser parser = new Parser();
    private final Context context = new Context();

    public void execute(String[] args) {
        logger.info("calculator starts working");
        try {
            Fabric fabric = new Fabric();
            var commandsAndTheirArgs = parser.parse(args);

            for (var commandAndArgs: commandsAndTheirArgs) {
                Command command = fabric.getCommand(commandAndArgs.get(0));

                try {
                    logger.info(commandAndArgs.get(0) +  " command starts working with context: "
                            + context + " and with args: " + commandAndArgs);

                    command.execute(context,commandAndArgs);

                } catch (Exception exception) {
                    logger.error(exception.getMessage(),exception);
                }
            }
        } catch (Exception exception) {
            logger.error(exception.getMessage(),exception);
        }
        logger.info("calculator finishes work");
    }
}

