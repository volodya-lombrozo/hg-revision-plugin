package domain;


import util.DefaultLogger;

import java.util.Properties;
import java.util.logging.Logger;

public class Loggable implements RecordableProperty {
    private final Logger logger;
    private final RecordableProperty delegate;

    public Loggable(RecordableProperty property) {
        this(property, new DefaultLogger().toLogger());
    }

    public Loggable(RecordableProperty delegate, Logger logger) {
        this.delegate = delegate;
        this.logger = logger;
    }

    @Override
    public void fillProperties(Properties properties) {
        logger.info("Search property " + delegate.getClass().getSimpleName());
        delegate.fillProperties(properties);
    }

    boolean loggerIsNotNull() {
        return logger != null;
    }

    String loggerName() {
        return logger.getName();
    }
}
