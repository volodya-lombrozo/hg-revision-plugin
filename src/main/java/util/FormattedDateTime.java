package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormattedDateTime {
    private final ZonedDateTime time;
    private final DateTimeFormatter formatter;

    public FormattedDateTime(Date date) {
        this(new OldZonedDateTime(date).toZonedDateTime());
    }

    public FormattedDateTime(ZonedDateTime time) {
        this(time, DateTimeFormatter.ISO_DATE_TIME);
    }

    public FormattedDateTime(ZonedDateTime time, DateTimeFormatter formatter) {
        this.time = time;
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        if (time == null || time.equals(new MinZonedDateTime().min()))
            return "";
        else return formatter.format(time);
    }
}
