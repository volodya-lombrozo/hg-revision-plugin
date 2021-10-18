package util.log;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EnabledLogTest {

    @Test
    public void enabled() {
        TestLog delegate = new TestLog();
        String message = "Some message";
        EnabledLog log = new EnabledLog(delegate, true);

        log.info(message);

        assertEquals(message, delegate.lastMessage());
    }

    @Test(expected = NoSuchElementException.class)
    public void disabled() {
        TestLog delegate = new TestLog();
        EnabledLog log = new EnabledLog(delegate, false);
        log.info("Some message");

        delegate.lastMessage();

        fail();
    }

    @Test
    public void name() {
        TestLog delegate = new TestLog();
        Log enabledLog = new EnabledLog(delegate);

        String actual = enabledLog.name();

        assertEquals(delegate.name(), actual);
    }
}