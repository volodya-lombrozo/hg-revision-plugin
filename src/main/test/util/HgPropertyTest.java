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

    @Test
    public void conflictedProperties() {
        String rawInput = "branch:'update_delete_game'\n" +
                "date:'1571136531 -10800'\n";

        String branch = new HgProperty(rawInput, "branch").property();
        String date = new HgProperty(rawInput, "date").property();

        assertEquals("update_delete_game", branch);
        assertEquals("1571136531 -10800", date);
    }
}