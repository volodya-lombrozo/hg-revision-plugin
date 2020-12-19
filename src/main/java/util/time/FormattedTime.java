package util.time;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormattedTime {
    private final ZonedDateTime time;
    private final DateTimeFormatter formatter;

    public FormattedTime(Date date) {
        this(new OldZonedDateTime(date).toZonedDateTime());
    }

    public FormattedTime(ZonedDateTime time) {
        this(time, DateTimeFormatter.ISO_DATE_TIME);
    }

    public FormattedTime(ZonedDateTime time, DateTimeFormatter formatter) {
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
