package util.time;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class StringHgTimeTest {

    @Test
    public void toZonedDateTime() {
        String seconds = "1567677484";
        String offsetSeconds = "-10800";
        String raw = seconds + " " + offsetSeconds;
        Instant sec = Instant.ofEpochSecond(Integer.parseInt(seconds));
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(-1 * Integer.parseInt(offsetSeconds));
        ZonedDateTime expected = ZonedDateTime.ofInstant(sec, zoneOffset);
        StringHgTime date = new StringHgTime(raw);

        ZonedDateTime actual = date.toZonedDateTime();

        assertEquals(expected, actual);
    }


    @Test
    public void empty() {
        StringHgTime date = new StringHgTime("");
        ZonedDateTime expected = new MinZonedDateTime().min();

        ZonedDateTime actual = date.toZonedDateTime();

        assertEquals(expected, actual);
    }
}