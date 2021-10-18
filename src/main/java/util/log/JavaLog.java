package util.log;

import java.util.logging.Logger;

public class JavaLog implements Log {

    private final Logger logger;

    public JavaLog() {
        this(Logger.getLogger("hg-revision-plugin"));
    }

    public JavaLog(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(final String message) {
        logger.info(message);
    }

    @Override
    public String name() {
        return logger.getName();
    }
}
