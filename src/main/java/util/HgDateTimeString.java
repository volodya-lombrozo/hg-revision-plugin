package util;

import java.time.ZonedDateTime;

public class HgDateTimeString {
    private final ZonedDateTime time;

    public HgDateTimeString(ZonedDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        int seconds = (int) time.toInstant().getEpochSecond();
        int offset = (-1) * time.getOffset().getTotalSeconds();
        return seconds + " " + offset;
    }
}
