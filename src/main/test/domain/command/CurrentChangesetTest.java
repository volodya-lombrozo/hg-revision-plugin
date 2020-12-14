package domain.command;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CurrentChangesetTest {

    @Test
    public void execute() throws ExecuteException {
        Command delegate = Mockito.mock(Command.class);
        CurrentChangesetCommand changeset = new CurrentChangesetCommand(delegate);
        String expected = "expected";
        when(delegate.execute()).thenReturn(expected);

        String res = changeset.execute();

        assertEquals(expected, res);
    }

    @Test
    public void defaultConstructor() {
        CurrentChangesetCommand changeset = new CurrentChangesetCommand("/some/path");

        boolean isNotNullDelegate = changeset.isNotNullDelegate();

        assertTrue(isNotNullDelegate);
    }
}