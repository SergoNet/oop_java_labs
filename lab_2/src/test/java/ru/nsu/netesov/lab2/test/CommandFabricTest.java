package ru.nsu.netesov.lab2.test;

import org.junit.jupiter.api.Test;
import ru.nsu.netesov.lab2.CommandFabric;
import ru.nsu.netesov.lab2.exceptions.PropertyNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;;

public class CommandFabricTest {

    @Test
    public void checkFabricWork() {
        String expected = "property " + UtilityForTests.fictiveCommand + " not found";
        Exception exception = assertThrows(PropertyNotFoundException.class,
                () -> CommandFabric.getInstance().create(UtilityForTests.fictiveCommand));
        assertEquals(expected,exception.getMessage());
    }
}
