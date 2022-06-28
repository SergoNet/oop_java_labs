package ru.nsu.netesov.lab2.test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.commands.PushCommand;
import ru.nsu.netesov.lab2.commands.SqrtCommand;
import ru.nsu.netesov.lab2.exceptions.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SqrtCommandTest {
    private static Command push;
    private static Command sqrt;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        sqrt = new SqrtCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("SQRT");

        String expected = "stack is empty";
        Exception exception = assertThrows(EmptyStackException.class,
                () -> sqrt.execute(context,args));

        assertEquals(expected,exception.getMessage());

        args.add(UtilityForTests.correctValue3);

        push.execute(context,args);
        push.execute(context,args);
        sqrt.execute(context,args);

        Double expectedNumber = 4.0;
        assertEquals(expectedNumber,context.peek());
    }
}