package util.time;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class OldZonedDateTime {
    private final Date date;

    public OldZonedDateTime(Date date) {
        this.date = date;
    }

    public ZonedDateTime toZonedDateTime() {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault());
    }
}
