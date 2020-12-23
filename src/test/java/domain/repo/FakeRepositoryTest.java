package domain.repo;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FakeRepositoryTest {

    private FakeRepository repo = new FakeRepository();

    @Test(expected = UnsupportedOperationException.class)
    public void bookmarks() {
        repo.bookmarks();
        fail();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void currentChangeset() {
        repo.currentChangeset();
        fail();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void findChangeset() {
        repo.findChangeset("");
        fail();
    }

    @Test
    public void path() {
        assertTrue(repo.path().isEmpty());
    }

    @Test
    public void testToString() {
        String expected = "FakeRepository#" + repo.hashCode();

        String actual = repo.toString();

        assertEquals(expected, actual);
    }
}