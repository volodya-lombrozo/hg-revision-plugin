package util.log;

import java.util.logging.Logger;

public class JavaLogger implements Log {

    private final Logger logger;

    public JavaLogger() {
        this(Logger.getLogger("hg-revision-plugin"));
    }

    public JavaLogger(Logger logger) {
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
