package domain;


import org.junit.Test;
import org.mockito.Mockito;
import util.DefaultLogger;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoggableTest {


    @Test
    public void logTest() {
        Logger mockLogger = Mockito.mock(Logger.class);
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

        assertTrue(loggable.loggerIsNotNull());
        String expectedLoggerName = new DefaultLogger().toLogger().getName();
        assertEquals(expectedLoggerName, loggable.loggerName());
    }

}