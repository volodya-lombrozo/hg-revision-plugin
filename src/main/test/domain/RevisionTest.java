package domain;

import com.aragost.javahg.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RevisionTest {

    private Changeset changeset;
    private String expected;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expected = "1";
        when(changeset.getRevision()).thenReturn(Integer.parseInt(expected));
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        Recordable revision = new Revision(changeset);

        revision.fillProperties(properties);

        String key = "hg.rev";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        Recordable revision = new Revision(changeset);

        String actual = revision.toString();

        assertEquals(expected, actual);
    }
}