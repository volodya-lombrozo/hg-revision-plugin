package domain.properties;

import domain.properties.Bookmarks;
import domain.properties.RecordableProperty;
import util.exceptions.ExecuteException;
import domain.repo.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BookmarksTest {

    private Repository repository;
    private String expected;

    @Before
    public void setUp() throws ExecuteException {
        repository = Mockito.mock(Repository.class);
        expected = "Bookmarks";
        when(repository.bookmarks()).thenReturn(Collections.singletonList(expected));
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        RecordableProperty bookmarks = new Bookmarks(repository);

        bookmarks.fillProperties(properties);

        String key = "hg.bookmarks";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        RecordableProperty bookmarks = new Bookmarks(repository);

        String actual = bookmarks.toString();

        assertEquals(expected, actual);
    }


    @Test(expected = RuntimeException.class)
    public void testToString_fail() throws ExecuteException {
        Repository failedRepo = Mockito.mock(Repository.class);
        RecordableProperty bookmarks = new Bookmarks(failedRepo);
        when(failedRepo.bookmarks()).thenThrow(new ExecuteException("Some execute exception"));

        String actual = bookmarks.toString();

        fail(actual + " bookmarks should not be presented");
    }
}