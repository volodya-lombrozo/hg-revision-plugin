package domain.command;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandTest {

    @Test
    public void fakeCommand() {
        Command.FakeCommand command = new Command.FakeCommand();

        String res = command.execute();

        assertTrue(res.isEmpty());
    }

}