package util.time;

import java.time.ZonedDateTime;

public class ZonedHgTime {
    private final ZonedDateTime time;

    public ZonedHgTime(ZonedDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        int seconds = (int) time.toInstant().getEpochSecond();
        int offset = (-1) * time.getOffset().getTotalSeconds();
        return seconds + " " + offset;
    }
}
