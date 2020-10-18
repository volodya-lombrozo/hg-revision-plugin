package domain;

import com.aragost.javahg.Repository;
import domain.util.BookmarksExtractor;
import domain.util.FakeExtractor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;

public class BookmarksTest {

    private Repository repository;
    private BookmarksExtractor extractor;
    private String expected;

    @Before
    public void setUp() {
        repository = Mockito.mock(Repository.class);
        expected = "Bookmarks";
        extractor = new FakeExtractor(expected);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        RecordableProperty bookmarks = new Bookmarks(repository, extractor);

        bookmarks.fillProperties(properties);

        String key = "hg.bookmarks";
        assertTrue(properties.containsKey(key));
        assertEquals(expected, properties.get(key));
    }

    @Test
    public void testToString() {
        RecordableProperty bookmarks = new Bookmarks(repository, extractor);

        String actual = bookmarks.toString();

        assertEquals(expected, actual);
    }
}