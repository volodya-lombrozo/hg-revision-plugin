package util;

import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class HgDateTimeTest {

    @Test
    public void toInstant() {
        String raw = "2020-10-19 11:53 +0300";
        HgDateTime date = new HgDateTime(raw);
        Instant expected = ZonedDateTime.parse(raw, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ZZZ")).toInstant();

        Instant actual = date.toInstant();

        assertEquals(expected, actual);
    }

}