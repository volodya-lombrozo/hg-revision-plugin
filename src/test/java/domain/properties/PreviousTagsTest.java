package domain.properties;

import domain.properties.PreviousTags;
import domain.properties.RecordableProperty;
import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PreviousTagsTest {

    private Changeset changeset;
    private final String firstTag = "a";
    private final String secondTag = "b";
    private final List<String> expectedTags = Arrays.asList(firstTag, secondTag);

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        Changeset firstChild = Mockito.mock(Changeset.class);
        Changeset secondChild = Mockito.mock(Changeset.class);
        when(changeset.getLeftParent()).thenReturn(firstChild);
        when(firstChild.getRightParent()).thenReturn(secondChild);
        ZonedDateTime dateTime = ZonedDateTime.now();
        when(changeset.getZonedDateTime()).thenReturn(dateTime);
        when(firstChild.getZonedDateTime()).thenReturn(dateTime);
        when(secondChild.tags()).thenReturn(expectedTags);
        when(secondChild.getZonedDateTime()).thenReturn(dateTime);
    }

    @Test
    public void fillProperties() {
        RecordableProperty previousTags = new PreviousTags(changeset);
        Properties properties = new Properties();

        previousTags.fillProperties(properties);

        assertTrue(properties.containsKey("hg.tags.previous"));
        assertTrue(properties.containsKey("hg.tags.previous[0]"));
        assertTrue(properties.containsKey("hg.tags.previous[1]"));
        assertTrue(properties.containsKey("hg.tag.previous"));
        assertEquals(properties.get("hg.tags.previous"), String.join(";", expectedTags));
        assertEquals(properties.get("hg.tags.previous[0]"), firstTag);
        assertEquals(properties.get("hg.tags.previous[1]"), secondTag);
        assertEquals(properties.get("hg.tag.previous"), firstTag);
        assertEquals(properties.get("hg.commit.number.from.previous.tag"), "2");
    }


    @Test
    public void commitNumberFromPreviousTag_tagsNotFound() {
        RecordableProperty previousTags = new PreviousTags(Mockito.mock(Changeset.class));
        Properties properties = new Properties();

        previousTags.fillProperties(properties);

        assertEquals(properties.get("hg.commit.number.from.previous.tag"), "");
    }

    @Test
    public void fillProperties_parentChangeSetIsNull() {
        RecordableProperty previousTags = new PreviousTags(Mockito.mock(Changeset.class));
        Properties properties = new Properties();

        previousTags.fillProperties(properties);

        assertTrue(properties.containsKey("hg.tags.previous"));
        assertFalse(properties.containsKey("hg.tags.previous[0]"));
        assertFalse(properties.containsKey("hg.tags.previous[1]"));
        assertTrue(properties.containsKey("hg.tag.previous"));
        assertTrue(properties.get("hg.tags.previous").toString().isEmpty());
        assertTrue(properties.get("hg.tag.previous").toString().isEmpty());
    }

    @Test
    public void testToString() {
        PreviousTags previousTags = new PreviousTags(changeset);

        String actual = previousTags.toString();

        System.out.println(actual);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }


    @Test
    public void timedTagTestLatest_secondLater() {
        ZonedDateTime now = ZonedDateTime.now();
        PreviousTags.TimedTags first = new PreviousTags.TimedTags(1, now, Arrays.asList("a", "b"));
        PreviousTags.TimedTags second = new PreviousTags.TimedTags(2, now.plusSeconds(1), Arrays.asList("c", "d"));

        PreviousTags.TimedTags later = PreviousTags.TimedTags.later(first, second);

        assertEquals(later, second);
    }

    @Test
    public void timedTagTestLatest_firstLater() {
        ZonedDateTime now = ZonedDateTime.now();
        PreviousTags.TimedTags first = new PreviousTags.TimedTags(1, now.plusSeconds(1), Arrays.asList("a", "b"));
        PreviousTags.TimedTags second = new PreviousTags.TimedTags(2, now, Arrays.asList("c", "d"));

        PreviousTags.TimedTags later = PreviousTags.TimedTags.later(first, second);

        assertEquals(later, first);
    }
}