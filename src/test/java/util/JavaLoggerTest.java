package util;

import org.junit.Test;
import util.log.JavaLogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JavaLoggerTest {

    @Test
    public void defaultConstructor() {
        JavaLogger javaLogger = new JavaLogger();
        assertEquals("hg-revision-plugin", javaLogger.name());
    }
}