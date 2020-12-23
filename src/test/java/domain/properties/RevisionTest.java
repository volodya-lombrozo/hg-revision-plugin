package domain.properties;

import domain.properties.RecordableProperty;
import domain.properties.Revision;
import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RevisionTest {

    private Changeset changeset;
    private String expected;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expected = "1";
        when(changeset.getRevision()).thenReturn(expected);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        RecordableProperty revision = new Revision(changeset);

        revision.fillProperties(properties);

        String key = "hg.rev";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        RecordableProperty revision = new Revision(changeset);

        String actual = revision.toString();

        assertEquals(expected, actual);
    }
}