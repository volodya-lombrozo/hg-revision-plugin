package util.log;

import org.apache.maven.plugin.logging.Log;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class MavenLogTest {

    @Test
    public void name() {
        MavenLog log = new MavenLog(Mockito.mock(Log.class));
        assertNotNull(log.name());
    }
}