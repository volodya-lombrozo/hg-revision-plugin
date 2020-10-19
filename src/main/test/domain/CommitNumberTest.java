package domain;

import com.aragost.javahg.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CommitNumberTest {

    private final Changeset changeset = Mockito.mock(Changeset.class);

    @Before
    public void setUp() {
        String expectedBranch = "branch";
        when(changeset.getBranch()).thenReturn(expectedBranch);
        Changeset child = Mockito.mock(Changeset.class);
        when(child.getBranch()).thenReturn(expectedBranch);
        when(changeset.getParent2()).thenReturn(child);
    }

    @Test
    public void fillProperties() {
        RecordableProperty commitNumber = new CommitNumber(changeset);
        Properties properties = new Properties();

        commitNumber.fillProperties(properties);

        String key = "hg.commit.number";
        assertTrue(properties.containsKey(key));
        assertEquals("2", properties.get(key));
    }
}