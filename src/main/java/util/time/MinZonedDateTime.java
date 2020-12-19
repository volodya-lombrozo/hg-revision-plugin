package util.time;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class MinZonedDateTime {

    private final Instant instant = Instant.ofEpochMilli(Long.MIN_VALUE);
    private final ZoneOffset utc = ZoneOffset.UTC;

    public ZonedDateTime min() {
        return ZonedDateTime.ofInstant(instant, utc);
    }
}
