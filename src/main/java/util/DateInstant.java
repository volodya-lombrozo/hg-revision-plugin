package util;

import java.time.Instant;
import java.util.Date;

public class DateInstant {
    private final Date date;

    public DateInstant(Date date) {
        this.date = date;
    }

    public Instant toInstant() {
        if (date == null) return null;
        return date.toInstant();
    }
}
