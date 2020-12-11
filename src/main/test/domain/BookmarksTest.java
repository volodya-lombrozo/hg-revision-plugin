package domain;

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
    public void setUp() {
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
}