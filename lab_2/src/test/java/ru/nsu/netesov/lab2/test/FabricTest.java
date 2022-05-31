package ru.nsu.netesov.lab2.test;

import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.Calculator;
import ru.nsu.netesov.lab2.Fabric;
import ru.nsu.netesov.lab2.Utility;
import ru.nsu.netesov.lab2.exceptions.CantCreateCommandException;
import ru.nsu.netesov.lab2.exceptions.FabricExceptions;

import static org.junit.jupiter.api.Assertions.assertSame;
//import static sun.security.tools.keytool.Main.Command.getCommand;

import org.junit.jupiter.api.Assertions;

public class FabricTest {
    @Test
    public void checkFabricWork() throws FabricExceptions, CantCreateCommandException {
        Fabric fabric = new Fabric();
        assertSame(fabric.getMapCommands().get("+"), fabric.getCommand("+"));
        assertSame(fabric.getMapCommands().get("-"), fabric.getCommand("-"));
        assertSame(fabric.getMapCommands().get("*"), fabric.getCommand("*"));
        assertSame(fabric.getMapCommands().get("/"), fabric.getCommand("/"));
        assertSame(fabric.getMapCommands().get("SQRT"), fabric.getCommand("SQRT"));
        assertSame(fabric.getMapCommands().get("PUSH"), fabric.getCommand("PUSH"));
        assertSame(fabric.getMapCommands().get("POP"), fabric.getCommand("POP"));
        assertSame(fabric.getMapCommands().get("DEFINE"), fabric.getCommand("DEFINE"));
        assertSame(fabric.getMapCommands().get("PRINT"), fabric.getCommand("PRINT"));
        assertSame(fabric.getMapCommands().get("SAVE"),fabric.getCommand("SAVE"));
        assertSame(fabric.getMapCommands().get("LOAD"), fabric.getCommand("LOAD"));
        assertSame(fabric.getMapCommands().get("ABS"), fabric.getCommand("ABS"));

        CantCreateCommandException thrown1 = Assertions.assertThrows(CantCreateCommandException.class, () -> {
            fabric.getCommand("1");
        }, "Exeption not thrown");
        Assertions.assertEquals(Utility.EXEPTION_INCORRECT_COMMAND, thrown1.getMessage());
    }
}
