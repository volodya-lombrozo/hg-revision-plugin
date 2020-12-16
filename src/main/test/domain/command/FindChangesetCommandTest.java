package domain.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

}