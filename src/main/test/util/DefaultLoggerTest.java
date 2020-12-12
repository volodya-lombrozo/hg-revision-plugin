package util;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.*;

public class DefaultLoggerTest {

    @Test
    public void passLoggerToConstructor() {
        Logger expected = Logger.getLogger("Any name");

        Logger actual = new DefaultLogger(expected).toLogger();

        assertEquals(expected, actual);
    }


    @Test
    public void defaultConstructor() {
        DefaultLogger defaultLogger = new DefaultLogger();

        Logger logger = defaultLogger.toLogger();

        assertNotNull(logger);
        assertEquals("Hg-revision-plugin", logger.getName());
    }
}