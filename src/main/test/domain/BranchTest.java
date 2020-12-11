package domain;

import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BranchTest {

    private Changeset changeset;
    private String expected;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expected = "Branch";
        when(changeset.getBranch()).thenReturn(expected);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        Branch branch = new Branch(changeset);

        branch.fillProperties(properties);

        String key = "hg.branch";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        Branch branch = new Branch(changeset);

        String actual = branch.toString();

        assertEquals(expected, actual);
    }
}