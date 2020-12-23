package domain.command;

import org.junit.Test;
import util.exceptions.ExecuteException;

import static org.junit.Assert.*;

public class FindChangesetCommandTest {

    @Test
    public void execute() throws ExecuteException {
        FindChangesetCommand command = new FindChangesetCommand(rev -> new Command.FakeCommand());

        String res = command.execute("rev");

        assertEquals(new Command.FakeCommand().execute(), res);
    }

    @Test
    public void repoPathConstructor() {
        String repoPath = "repoPath";

        FindChangesetCommand command = new FindChangesetCommand(repoPath);

        assertTrue(command.commandFactoryIsInstalled());
    }


    @Test(expected = ExecuteException.class)
    public void emptyParam() throws ExecuteException {
        String[] empty = new String[0];
        FindChangesetCommand command = new FindChangesetCommand("/some/path");

        command.execute(empty);

        fail();
    }

}