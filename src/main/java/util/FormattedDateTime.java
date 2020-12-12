package util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormattedDateTime {
    private final Instant instant;
    private final DateTimeFormatter formatter;

    public FormattedDateTime(Date date) {
        this(new DateInstant(date).toInstant());
    }

    public FormattedDateTime(Instant instant) {
        this(instant, DateTimeFormatter.ISO_DATE_TIME);
    }

    public FormattedDateTime(Instant instant, DateTimeFormatter formatter) {
        this.instant = instant;
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        if (instant == null) return "";
        else return formatter.format(instant.atZone(ZoneId.systemDefault()));
    }
}
