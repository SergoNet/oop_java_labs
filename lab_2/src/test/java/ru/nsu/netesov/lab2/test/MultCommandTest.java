package ru.nsu.netesov.lab2.test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.commands.MultCommand;
import ru.nsu.netesov.lab2.commands.PushCommand;
import ru.nsu.netesov.lab2.exceptions.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultCommandTest {
    private static Command push;
    private static Command mult;

    @BeforeAll
    public static void setUp() {
        push = new PushCommand();
        mult = new MultCommand();
    }

    @Test
    public void checkCommandWork() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("*");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);

        String expected = "stack is empty";
        Exception exception = assertThrows(EmptyStackException.class,
                () -> mult.execute(context,args));

        assertEquals(expected,exception.getMessage());

        pushValueNTimes(context,args,2);
        mult.execute(context,args);

        Double expectedNumber = Double.parseDouble(UtilityForTests.correctValue1) *
                Double.parseDouble(UtilityForTests.correctValue1);
        assertEquals(expectedNumber,context.peek());
    }

    private void pushValueNTimes(Context context, List<String> args, int N) {
        for (int i = 0; i < N; i++) {
            push.execute(context,args);
        }
    }
}

