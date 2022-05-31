package ru.nsu.netesov.lab2.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.commands.AddCommand;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.commands.PushCommand;
import ru.nsu.netesov.lab2.exceptions.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    private static Command push;
    private static Command add;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        add = new AddCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("+");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);

        String expected = "stack is empty";
        Exception exception = assertThrows(EmptyStackException.class,
                () -> add.execute(context,args));

        assertEquals(expected,exception.getMessage());

        push.execute(context,args);
        push.execute(context,args);
        add.execute(context,args);

        Double expectedNumber = Double.parseDouble(UtilityForTests.correctValue1) * 2;
        assertEquals(expectedNumber,context.peek());
    }
}
