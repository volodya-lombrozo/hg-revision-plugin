package util.exceptions;

import org.junit.Test;
import util.exceptions.ExecuteException;

import static org.junit.Assert.assertEquals;

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