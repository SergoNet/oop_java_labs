package ru.nsu.netesov.lab2.test;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Context;
import ru.nsu.netesov.lab2.commands.Command;
import ru.nsu.netesov.lab2.commands.PopCommand;
import ru.nsu.netesov.lab2.commands.PushCommand;
import ru.nsu.netesov.lab2.exceptions.EmptyStackException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PopTest {
    private static Command pop;
    private static Command push;

    @BeforeAll
    public static void setUp() {
        pop = new PopCommand();
        push = new PushCommand();
    }

    @Test
    public void checkChangeOfStackSize() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("POP");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);
        pop.execute(context,args);

        int expected = 0;
        assertEquals(expected,context.getStackSize());
    }

    @Test
    public void checkChangeOfStack() {
        Context context = new Context();
        List<String> args = new ArrayList<>();
        args.add("POP");

        args.add(UtilityForTests.correctValue1);
        push.execute(context,args);
        UtilityForTests.removeAndAddArg(args,1,UtilityForTests.correctValue2);
        push.execute(context,args);
        pop.execute(context,args);
        assertEquals(Double.parseDouble(UtilityForTests.correctValue1),context.peek());

        pop.execute(context,args);

        String expected = "stack is empty";

        Exception exception = assertThrows(EmptyStackException.class,() -> {
            pop.execute(context,args);
        });

        assertEquals(expected,exception.getMessage());
    }
}

