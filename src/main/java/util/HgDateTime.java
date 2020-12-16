package util;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class HgDateTime {
    private final String raw;
    private final DateTimeFormatter formatter;

    public HgDateTime(String raw) {
        this(raw, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm ZZZ"));
    }

    public HgDateTime(String raw, DateTimeFormatter formatter) {
        this.raw = raw;
        this.formatter = formatter;
    }

    public Instant toInstant() {
        return ZonedDateTime.parse(raw, formatter).toInstant();
    }
}
