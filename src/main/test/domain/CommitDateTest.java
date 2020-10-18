package domain;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class CommitDateTest {

    private Changeset changeset;
    private Date date = new Date();
    private String expectedDate = DateTimeFormatter.ISO_DATE_TIME.format(date.toInstant().atZone(ZoneId.systemDefault()));


    @Before
    public void setUp() throws Exception {
        changeset = Mockito.mock(Changeset.class);
        DateTime mock = Mockito.mock(DateTime.class);
        when(mock.getDate()).thenReturn(date);
        when(changeset.getTimestamp()).thenReturn(mock);
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