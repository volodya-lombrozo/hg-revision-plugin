package domain.properties;


import org.junit.Test;
import org.mockito.Mockito;
import util.log.JavaLogger;
import util.log.Log;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoggableTest {


    @Test
    public void logTest() {
        Log mockLogger = Mockito.mock(Log.class);
        RecordableProperty.Fake property = new RecordableProperty.Fake();
        String expectedMessage = "Search property " + property.getClass().getSimpleName();
        Loggable loggable = new Loggable(property, mockLogger);

        loggable.fillProperties(new Properties());

        verify(mockLogger, times(1)).info(expectedMessage);
    }

    @Test
    public void logTestWithDefaultConstructor() {
        RecordableProperty.Fake property = new RecordableProperty.Fake();
        Loggable loggable = new Loggable(property);

        loggable.fillProperties(new Properties());

        assertTrue(loggable.loggerIsNotEmpty());
        String expectedLoggerName = new JavaLogger().name();
        assertEquals(expectedLoggerName, loggable.loggerName());
    }

}