package domain.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExecuteExceptionTest {

    @Test
    public void messageOnly() {
        String expected = "message";
        ExecuteException exception = new ExecuteException(expected);

        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }


    @Test
    public void messageAndCause() {
        Exception cause = new Exception();
        ExecuteException exception = new ExecuteException("message", cause);

        Throwable actualCause = exception.getCause();

        assertEquals(cause, actualCause);
    }

}