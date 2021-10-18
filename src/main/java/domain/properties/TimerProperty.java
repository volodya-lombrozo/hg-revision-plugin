package domain.properties;

import util.log.JavaLog;
import util.log.Log;

import java.util.Properties;

public class TimerProperty implements RecordableProperty {

    private final RecordableProperty property;
    private final Log logger;

    public TimerProperty(RecordableProperty property) {
        this(property, new JavaLog());
    }

    public TimerProperty(RecordableProperty property, Log logger) {
        this.property = property;
        this.logger = logger;
    }

    @Override
    public void fillProperties(Properties properties) {
        long start = System.nanoTime();
        property.fillProperties(properties);
        long finish = System.nanoTime();
        String timerMessage = String.format("Searching time for property '%s': %f ms", property.getClass().getSimpleName(), ((finish - start) / 1_000_000d));
        logger.info(timerMessage);
    }

    boolean loggerIsDefined() {
        return logger != null;
    }
}
