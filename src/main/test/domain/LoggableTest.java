package domain;


import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;
import java.util.logging.Logger;

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

}