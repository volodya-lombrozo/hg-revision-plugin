package domain;

import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AuthorTest {

    private Changeset changeset;
    private String expectedAuthor;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        expectedAuthor = "Author";
        when(changeset.getUser()).thenReturn(expectedAuthor);
    }

    @Test
    public void fillProperties() {
        Properties properties = new Properties();
        Author author = new Author(changeset);

        author.fillProperties(properties);

        String key = "hg.author";
        assertTrue(properties.containsKey(key));
        assertEquals(expectedAuthor, properties.get(key));
    }

    @Test
    public void testToString() {
        Author author = new Author(changeset);

        String actual = author.toString();

        assertEquals(expectedAuthor, actual);
    }
}