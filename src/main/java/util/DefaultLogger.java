package util;

import java.util.logging.Logger;

public class DefaultLogger {

    private final Logger logger;

    public DefaultLogger() {
        this(Logger.getLogger("Hg-revision-plugin"));
    }

    public DefaultLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger toLogger() {
        return logger;
    }
}
