package domain;

import util.exceptions.ExecuteException;
import domain.repo.Changeset;
import domain.repo.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.time.FormattedTime;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class RepositoryInfoTest {

    Repository repository;

    @Before
    public void setUp() throws ExecuteException {
        repository = Mockito.mock(Repository.class);
        Changeset changeset = Mockito.mock(Changeset.class);
        when(repository.currentChangeset()).thenReturn(changeset);
        when(changeset.getBranch()).thenReturn("branch");
        when(changeset.getZonedDateTime()).thenReturn(ZonedDateTime.now());
        when(changeset.getFormattedDateTime()).thenReturn(new FormattedTime(ZonedDateTime.now()).toString());
        when(changeset.getLeftParent()).thenReturn(null);
        when(changeset.getRightParent()).thenReturn(null);
        when(changeset.getMessage()).thenReturn("message");
        when(changeset.getNode()).thenReturn("node");
        when(changeset.getRevision()).thenReturn("1");
        when(changeset.getUser()).thenReturn("user");
        when(changeset.tags()).thenReturn(Arrays.asList("firstTag", "secondTag"));
    }

    @Test
    public void fillPropertiesTest() throws ExecuteException {
        RepositoryInfo info = new RepositoryInfo(repository);
        Properties properties = new Properties();

        info.fillProperties(properties);

        assertFalse(properties.isEmpty());
        assertEquals(15, properties.size());
        assertPropertiesContainsKeys(properties, Arrays.asList("hg.author", "hg.bookmarks", "hg.branch", "hg.date",
                "hg.commit.number", "hg.desc", "hg.node", "hg.commit.number.from.previous.tag", "hg.rev", "hg.tags", "hg.tag", "hg.tag.previous", "hg.tags.previous",
                "hg.tags[0]", "hg.tags[1]"));
    }

    private void assertPropertiesContainsKeys(Properties properties, List<String> keys) {
        for (String key : keys) assertTrue(properties.containsKey(key));
    }

    @Test
    public void toStringTest() {
        RepositoryInfo info = new RepositoryInfo(repository);

        String actual = info.toString();

        assertNotNull(actual);
    }
}