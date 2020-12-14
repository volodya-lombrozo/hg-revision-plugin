package domain.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class OutputPropertyTest {

    @Test
    public void property_simpleValue() {
        String expected = "some value;";
        OutputProperty property = new OutputProperty("key:'" + expected + "'", "key");

        String actual = property.property();

        assertEquals(expected, actual);
    }

    @Test
    public void property_arrayValue() {
        String expected = "'[a; b; c]'";
        OutputProperty property = new OutputProperty("key:'" + expected + "'", "key");

        String actual = property.property();

        assertEquals(expected, actual);
    }
}