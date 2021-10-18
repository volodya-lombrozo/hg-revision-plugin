package domain.properties;


import util.log.JavaLog;
import util.log.Log;

import java.util.Properties;

public class Loggable implements RecordableProperty {
    private final Log log;
    private final RecordableProperty delegate;

    public Loggable(RecordableProperty property) {
        this(property, new JavaLog());
    }

    public Loggable(RecordableProperty delegate, Log logger) {
        this.delegate = delegate;
        this.log = logger;
    }

    @Override
    public void fillProperties(Properties properties) {
        log.info("Search property " + delegate.getClass().getSimpleName());
        delegate.fillProperties(properties);
    }

    public boolean loggerIsNotEmpty() {
        return log != null;
    }

    public String loggerName() {
        return log.name();
    }
}
