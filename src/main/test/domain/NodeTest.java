package domain;

import com.aragost.javahg.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class NodeTest {

    private Changeset changeset;
    private String expected;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expected = "nodeHash#123123";
        when(changeset.getNode()).thenReturn(expected);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        Recordable node = new Node(changeset);

        node.fillProperties(properties);

        String key = "hg.node";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        Recordable node = new Node(changeset);

        String actual = node.toString();

        assertEquals(expected, actual);
    }
}