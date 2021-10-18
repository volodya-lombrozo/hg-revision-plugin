package domain.properties;


import org.junit.Test;
import org.mockito.Mockito;
import util.log.Log;

import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TimerPropertyTest {

    @Test
    public void fillPropertyAndCalculateExecutionTime() {
        RecordableProperty delegate = new RecordableProperty.Fake();
        Log logger = Mockito.mock(Log.class);
        TimerProperty property = new TimerProperty(delegate, logger);

        property.fillProperties(new Properties());

        verify(logger, times(1)).info(anyString());
    }

    @Test
    public void defaultConstructor() {
        RecordableProperty delegate = new RecordableProperty.Fake();

        TimerProperty property = new TimerProperty(delegate);

        assertTrue(property.loggerIsDefined());
    }

    @Test
    public void nullLoggerTest() {
        RecordableProperty delegate = new RecordableProperty.Fake();

        TimerProperty property = new TimerProperty(delegate, null);

        assertFalse(property.loggerIsDefined());
    }
}