package util;

import org.junit.Test;
import util.log.JavaLog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JavaLogTest {

    @Test
    public void defaultConstructor() {
        JavaLog javaLog = new JavaLog();
        assertEquals("hg-revision-plugin", javaLog.name());
    }
}