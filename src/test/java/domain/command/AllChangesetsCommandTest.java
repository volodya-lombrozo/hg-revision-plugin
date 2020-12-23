package domain.command;

import org.junit.Test;
import org.mockito.Mockito;
import util.exceptions.ExecuteException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AllChangesetsCommandTest {

    @Test
    public void executeTest() throws ExecuteException {
        Command delegate = Mockito.mock(Command.class);
        AllChangesetsCommand command = new AllChangesetsCommand(delegate);

        command.execute();

        verify(delegate, times(1)).execute();
    }

}