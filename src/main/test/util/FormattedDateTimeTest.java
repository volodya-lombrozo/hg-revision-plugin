package util;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.*;

public class FormattedDateTimeTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Test
    public void plainDate() {
        Date date = new Date();
        FormattedDateTime dateTime = new FormattedDateTime(date);

        String actual = dateTime.toString();

        String expected = formatter.format(date.toInstant().atZone(ZoneId.systemDefault()));
        assertEquals(expected, actual);
    }

    @Test
    public void instant() {
        Instant instant = Instant.now();
        FormattedDateTime dateTime = new FormattedDateTime(instant, formatter);

        String actual = dateTime.toString();

        String expected = formatter.format(instant.atZone(ZoneId.systemDefault()));
        assertEquals(expected, actual);
    }

    @Test
    public void passNullPlainDate() {
        Date empty = null;
        FormattedDateTime dateTime = new FormattedDateTime(empty);

        String actual = dateTime.toString();

        assertEquals("", actual);
    }

    @Test
    public void passNullInstant() {
        Instant empty = null;
        FormattedDateTime dateTime = new FormattedDateTime(empty, formatter);

        String actual = dateTime.toString();

        assertEquals("", actual);
    }

}