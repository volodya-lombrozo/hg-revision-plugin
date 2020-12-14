package domain.repo;

import domain.command.Command;
import domain.command.ExecuteException;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CommandLineRepositoryTest {

    @Test
    public void currentChangeset() throws ExecuteException {
        Command changesetCommand = Mockito.mock(Command.class);
        Repository repo = new CommandLineRepository(changesetCommand, changesetCommand);
        String commandOutput = "user:'author'";
        Changeset expected = new CommandLineChangeset(commandOutput);
        when(changesetCommand.execute()).thenReturn(commandOutput);

        Changeset acutal = repo.currentChangeset();

        assertNotNull(acutal);
        assertEquals(expected, acutal);
    }


    @Test
    @Ignore("for manual testing only")
    public void integrationTest() {
        CommandLineRepository repository = new CommandLineRepository("D:\\workspace\\hg_repo");

        Changeset changeset = repository.currentChangeset();

        System.out.println(changeset);
        assertNotNull(changeset);
    }
}