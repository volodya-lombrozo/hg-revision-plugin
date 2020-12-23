package util.exceptions;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParentSearchExceptionTest {

    @Test
    public void defaultConstructor() {
        ParentSearchException ex = new ParentSearchException();

        String actual = ex.getMessage();

        assertEquals("Can't find parent", actual);
    }

    @Test
    public void causeOnly() {
        Exception cause = new Exception();

        ParentSearchException ex = new ParentSearchException(cause);

        assertEquals(cause, ex.getCause());
    }
}