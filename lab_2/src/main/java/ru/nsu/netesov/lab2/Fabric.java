package ru.nsu.netesov.lab2;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.exceptions.CantCreateCommandException;
import ru.nsu.netesov.lab2.exceptions.FabricExceptions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fabric {
    private static final Logger logger = LogManager.getLogger(Fabric.class);
    private final Map<String, Command> mapCommand = new HashMap<>();
    private BufferedReader reader;

    public Fabric() throws FabricExceptions {
        logger.info("properties was loaded");
        try {
            try {
                reader = new BufferedReader(new FileReader(Utility.FABRIC_CONFIGURATION_FILE_NAME));
                logger.info("opened the configuration file");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Pattern pattern = Pattern.compile(Utility.REGEX_FOR_CONFIGURATION_FABRIC);
            String string = reader.readLine();

            logger.info("added a startup program");
            while (string != null) {
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    String keyCommands = string.substring(matcher.start(), matcher.end());
                    if (matcher.find()) {
                        Class<?> executor = Class.forName(string.substring(matcher.start(), matcher.end()));
                        Command command = (Command) executor.getDeclaredConstructor().newInstance();
                        mapCommand.put(keyCommands, command);

                        string = reader.readLine();
                    } else {
                        logger.error("the factory caught an exception: " + Utility.EXEPTION_FABRIC_CONFIGURATION_FILE);
                        throw (new FabricExceptions(Utility.EXEPTION_FABRIC_CONFIGURATION_FILE));
                    }
                } else {
                    logger.error("the factory caught an exception: " + Utility.EXEPTION_FABRIC_CONFIGURATION_FILE);
                    throw (new FabricExceptions(Utility.EXEPTION_FABRIC_CONFIGURATION_FILE));
                }
            }
        } catch (Throwable exceptions) {
            exceptions.printStackTrace();
            throw (new FabricExceptions(Utility.EXEPTION_FABRIC_CONFIGURATION_FILE));
        }
    }

    public Command getCommand(String str) throws CantCreateCommandException {
        Command command = getMapCommands().get(str);
        if (command == null) {
            logger.error("the return command method caught an exception " + Utility.EXEPTION_INCORRECT_COMMAND);
            throw (new CantCreateCommandException(Utility.EXEPTION_INCORRECT_COMMAND));
        }
        return getMapCommands().get(str);
    }

    public Map<String, Command> getMapCommands() {
        return mapCommand;
    }
}
