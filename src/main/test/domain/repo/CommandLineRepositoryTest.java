package domain.repo;

import domain.command.Command;
import domain.command.ExecuteException;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CommandLineRepositoryTest {

    @Test
    public void currentChangeset() throws ExecuteException {
        Command changesetCommand = Mockito.mock(Command.class);
        Repository repo = new CommandLineRepository(changesetCommand, null);
        String commandOutput = "user:'author'";
        Changeset expected = new CommandLineChangeset(commandOutput);
        when(changesetCommand.execute()).thenReturn(commandOutput);

        Changeset acutal = repo.currentChangeset();

        assertNotNull(acutal);
        assertEquals(expected, acutal);
    }

    @Test
    public void bookmarks() throws ExecuteException {
        Command bookmarksCommand = Mockito.mock(Command.class);
        CommandLineRepository repo = new CommandLineRepository(null, bookmarksCommand);
        String first = "first";
        String second = "second";
        when(bookmarksCommand.execute()).thenReturn(first + "\n" + second);

        List<String> bookmarks = repo.bookmarks();

        assertEquals(2, bookmarks.size());
        assertTrue(bookmarks.contains(first));
        assertTrue(bookmarks.contains(second));
    }

    @Test
    public void defaultConstructor() {
        CommandLineRepository repo = new CommandLineRepository("/some/path");

        boolean notEmptyCommands = repo.notEmptyCommands();

        assertTrue(notEmptyCommands);
    }


    @Test
    @Ignore("for manual testing only")
    public void integrationTest() throws ExecuteException {
        CommandLineRepository repository = new CommandLineRepository("D:\\workspace\\hg_repo"); //pass your path there

        Changeset changeset = repository.currentChangeset();

        System.out.println(changeset);
        assertNotNull(changeset);
    }
}