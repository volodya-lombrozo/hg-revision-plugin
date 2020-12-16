package domain.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterizedCommandTest {

    @Test
    public void executeFakeCommand() {
        ParameterizedCommand.Fake command = new ParameterizedCommand.Fake();

        String res = command.execute("param");

        assertTrue(res.isEmpty());
    }
}