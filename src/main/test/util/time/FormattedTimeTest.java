package util.time;

import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FormattedTimeTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Test
    public void plainDate() {
        Date date = new Date();
        FormattedTime dateTime = new FormattedTime(date);

        String actual = dateTime.toString();

        String expected = formatter.format(date.toInstant().atZone(ZoneId.systemDefault()));
        assertEquals(expected, actual);
    }

    @Test
    public void instant() {
        ZonedDateTime time = ZonedDateTime.now();
        FormattedTime dateTime = new FormattedTime(time, formatter);

        String actual = dateTime.toString();

        String expected = formatter.format(time);
        assertEquals(expected, actual);
    }

    @Test
    public void passNullPlainDate() {
        Date empty = null;
        FormattedTime dateTime = new FormattedTime(empty);

        String actual = dateTime.toString();

        assertEquals("", actual);
    }

    @Test
    public void passNullInstant() {
        ZonedDateTime empty = null;
        FormattedTime dateTime = new FormattedTime(empty, formatter);

        String actual = dateTime.toString();

        assertEquals("", actual);
    }

}