package util.log;

import org.apache.maven.plugin.logging.Log;
import org.junit.Test;
import org.mockito.Mockito;

import java.security.PublicKey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MavenLogTest {

    @Test
    public void info() {
        Log mock = Mockito.mock(Log.class);
        String expected = "message";

        new MavenLog(mock).info(expected);

        verify(mock, times(1)).info(expected);
    }

    @Test
    public void name() {
        MavenLog log = new MavenLog(Mockito.mock(Log.class));
        assertNotNull(log.name());
    }
}