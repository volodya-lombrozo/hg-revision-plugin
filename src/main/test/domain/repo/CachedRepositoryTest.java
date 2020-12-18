package domain.repo;

import domain.command.Command;
import domain.command.ExecuteException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CachedRepositoryTest {
    Repository delegate;

    @Before
    public void setUp() {
        delegate = Mockito.mock(Repository.class);
        when(delegate.path()).thenReturn("/some/path");
    }

    @Test
    public void bookmarks_cached() throws ExecuteException {
        List<String> bookmarks = Arrays.asList("a", "b");
        CachedRepository repo = new CachedRepository(delegate, bookmarks);

        List<String> actual = repo.bookmarks();

        assertEquals(bookmarks, actual);
    }

    @Test
    public void findChangeset_cached() throws ExecuteException {
        CommandLineChangeset changeset = new CommandLineChangeset("node:'one'");
        CachedRepository repo = new CachedRepository(delegate, Collections.singletonMap("one", changeset));

        Changeset actual = repo.findChangeset("one");

        assertEquals(changeset, actual);
    }

    @Test
    public void currentChangeset_cached() throws ExecuteException {
        CommandLineChangeset changeset = new CommandLineChangeset("node:'one'");
        CachedRepository repo = new CachedRepository(delegate, new AtomicReference<>(changeset));

        Changeset actual = repo.currentChangeset();

        assertEquals(changeset, actual);
    }


    @Test
    public void bookmarks_load() throws ExecuteException {
        List<String> bookmarks = Arrays.asList("a", "b");
        when(delegate.bookmarks()).thenReturn(bookmarks);
        CachedRepository repo = new CachedRepository(delegate);

        List<String> actual = repo.bookmarks();

        assertEquals(bookmarks, actual);
    }

    @Test
    public void findChangeset_load() throws ExecuteException {
        String firstNode = "node:'first'";
        String secondNode = "node:'second'";
        Command findAllChangesetsCommand = Mockito.mock(Command.class);
        when(findAllChangesetsCommand.execute()).thenReturn(firstNode + "\n\n" + secondNode);
        CachedRepository repo = new CachedRepository(delegate, findAllChangesetsCommand);

        Changeset firstActual = repo.findChangeset("first");
        Changeset secondActual = repo.findChangeset("second");

        CommandLineChangeset first = new CommandLineChangeset(firstNode);
        CommandLineChangeset second = new CommandLineChangeset(secondNode);
        assertEquals(first, firstActual);
        assertEquals(second, secondActual);
    }

    @Test
    public void currentChangeset_load() throws ExecuteException {
        CommandLineChangeset changeset = new CommandLineChangeset("node:'one'");
        when(delegate.currentChangeset()).thenReturn(changeset);
        CachedRepository repo = new CachedRepository(delegate);

        Changeset actual = repo.currentChangeset();

        assertEquals(changeset, actual);
    }

    @Test
    public void path() {
        CachedRepository repo = new CachedRepository(delegate);

        String path = repo.path();

        assertEquals(delegate.path(), path);
    }
}