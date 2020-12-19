package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HgPropertyTest {

    @Test
    public void property_simpleValue() {
        String expected = "some value;";
        HgProperty property = new HgProperty("key:'" + expected + "'", "key");

        String actual = property.property();

        assertEquals(expected, actual);
    }

    @Test
    public void property_arrayValue() {
        String expected = "'[a; b; c]'";
        HgProperty property = new HgProperty("key:'" + expected + "'", "key");

        String actual = property.property();

        assertEquals(expected, actual);
    }
}