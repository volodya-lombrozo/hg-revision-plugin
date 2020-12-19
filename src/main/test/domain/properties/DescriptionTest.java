package domain.properties;

import domain.properties.Description;
import domain.properties.RecordableProperty;
import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class DescriptionTest {

    private Changeset changeset;
    private String expected;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expected = "Description";
        when(changeset.getMessage()).thenReturn(expected);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        RecordableProperty description = new Description(changeset);

        description.fillProperties(properties);

        String key = "hg.desc";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        RecordableProperty description = new Description(changeset);

        String actual = description.toString();

        assertEquals(expected, actual);
    }
}