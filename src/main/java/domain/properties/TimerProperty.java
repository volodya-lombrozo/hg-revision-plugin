package domain.properties;

import util.DefaultLogger;

import java.util.Properties;
import java.util.logging.Logger;

public class TimerProperty implements RecordableProperty {

    private final RecordableProperty property;
    private final Logger logger;

    public TimerProperty(RecordableProperty property) {
        this(property, new DefaultLogger().toLogger());
    }

    public TimerProperty(RecordableProperty property, Logger logger) {
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
