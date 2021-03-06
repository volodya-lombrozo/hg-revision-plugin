package util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class InputStringTest {

    @Test
    public void read() throws IOException {
        String expected = "expected";
        InputStream stream = new ByteArrayInputStream(expected.getBytes());
        StringInputStream inputString = new StringInputStream(stream);

        String actual = inputString.read();

        assertEquals(expected, actual);
    }

}