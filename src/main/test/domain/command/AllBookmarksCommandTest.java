package domain.command;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AllBookmarksCommandTest {

    @Test
    public void execute() throws ExecuteException {
        Command delegate = Mockito.mock(Command.class);
        String expected = "expected";
        when(delegate.execute()).thenReturn(expected);
        AllRepositoryBookmarksCommand command = new AllRepositoryBookmarksCommand(delegate);

        String actual = command.execute();

        assertEquals(expected, actual);
    }

    @Test
    public void isNotNullDelegate() {
        AllRepositoryBookmarksCommand command = new AllRepositoryBookmarksCommand("/some/path");

        boolean notNullDelegate = command.isNotNullDelegate();

        assertTrue(notNullDelegate);
    }
}