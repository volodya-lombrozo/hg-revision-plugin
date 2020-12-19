package domain.properties;

import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.time.FormattedDateTime;

import java.util.Date;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class CommitDateTest {

    private Changeset changeset;
    private final Date date = new Date();
    private final String expectedDate = new FormattedDateTime(date).toString();


    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        when(changeset.getFormattedDateTime()).thenReturn(expectedDate);
    }

    @Test
    public void fillProperties() {
        CommitDate commitDate = new CommitDate(changeset);
        Properties properties = new Properties();

        commitDate.fillProperties(properties);

        String key = "hg.date";
        assertTrue(properties.containsKey(key));
        assertEquals(expectedDate, properties.get(key));
    }

    @Test
    public void testToString() {
        CommitDate commitDate = new CommitDate(changeset);

        String actual = commitDate.toString();

        assertEquals(expectedDate, actual);
    }
}