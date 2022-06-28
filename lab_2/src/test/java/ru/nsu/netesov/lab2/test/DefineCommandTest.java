package ru.nsu.netesov.lab2.test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.commands.PushCommand;
import ru.nsu.netesov.lab2.commands.DefineCommand;
import ru.nsu.netesov.lab2.exceptions.IncorrectAmountOfArgsException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefineCommandTest {
    private static Command push;
    private static Command define;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        define = new DefineCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("DEFINE");

        args.add(UtilityForTests.key1);
        args.add(UtilityForTests.correctValue1);
        define.execute(context,args);
        args.remove(1);
        push.execute(context,args);

        Double expected = Double.parseDouble(UtilityForTests.correctValue1);
        assertEquals(expected,context.peek());
    }

    @Test
    public void checkArgsExceptions() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("DEFINE");


        String expected = "define command needs 2 args";
        Exception exception = assertThrows(IncorrectAmountOfArgsException.class,
                () -> define.execute(context,args));
        assertEquals(expected,exception.getMessage());

        args.add(UtilityForTests.key1);

        exception = assertThrows(IncorrectAmountOfArgsException.class,
                () -> define.execute(context,args));
        assertEquals(expected,exception.getMessage());

        args.add("sfsf");

        expected = "For input string: \"sfsf\"";
        exception = assertThrows(NumberFormatException.class,
                () -> define.execute(context,args));

        assertEquals(expected,exception.getMessage());
    }
}

