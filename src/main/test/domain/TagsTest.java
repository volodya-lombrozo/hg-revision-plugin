package domain;

import com.aragost.javahg.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TagsTest {

    private Changeset changeset;
    private final String firstTag = "a";
    private final String secondTag = "b";
    private final List<String> expectedTags = Arrays.asList(firstTag, secondTag);

    @Before
    public void setUp() throws Exception {
        changeset = Mockito.mock(Changeset.class);
        when(changeset.tags()).thenReturn(expectedTags);
    }

    @Test
    public void fillProperties() {
        Tags tags = new Tags(changeset);
        Properties properties = new Properties();

        tags.fillProperties(properties);

        assertTrue(properties.containsKey("hg.tags"));
        assertTrue(properties.containsKey("hg.tags[0]"));
        assertTrue(properties.containsKey("hg.tags[1]"));
        assertTrue(properties.containsKey("hg.tag"));
        assertEquals(properties.get("hg.tags"), String.join(";", expectedTags));
        assertEquals(properties.get("hg.tags[0]"), firstTag);
        assertEquals(properties.get("hg.tags[1]"), secondTag);
        assertEquals(properties.get("hg.tag"), firstTag);
    }

    @Test
    public void toMap() {
        Tags tags = new Tags(expectedTags);

        Map<String, String> map = tags.toMap();

        assertTrue(map.containsKey("hg.tags[0]"));
        assertTrue(map.containsKey("hg.tags[1]"));
        assertEquals(map.get("hg.tags[0]"), firstTag);
        assertEquals(map.get("hg.tags[1]"), secondTag);
    }

    @Test
    public void testToString() {
        Tags tags = new Tags(expectedTags);
        String expected = String.join(";", expectedTags);

        String actual = tags.toString();

        assertEquals(expected, actual);
    }
}