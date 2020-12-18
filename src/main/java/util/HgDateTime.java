package util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class HgDateTime {

    private final String raw;

    public HgDateTime(String raw) {
        this.raw = raw;
    }

    public ZonedDateTime toZonedDateTime() {
        if (raw == null || raw.isEmpty())
            return new MinZonedDateTime().min();
        String[] split = raw.split(" ");
        int seconds = Integer.parseInt(split[0]);
        ZoneOffset offset = ZoneOffset.ofTotalSeconds((-1) * Integer.parseInt(split[1]));
        return Instant.ofEpochSecond(seconds).atZone(offset);
    }
}
