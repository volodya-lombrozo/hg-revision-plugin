package domain.command;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.when;

public class HgCommandTest {

    @Test
    public void execute_successful() throws ExecuteException, IOException {
        ProcessFactory processBuilder = Mockito.mock(ProcessFactory.class);
        Process process = Mockito.mock(Process.class);
        when(processBuilder.start()).thenReturn(process);
        when(process.exitValue()).thenReturn(0);
        String expected = "res";
        when(process.getInputStream()).thenReturn(new ByteArrayInputStream(expected.getBytes()));
        HgCommand command = new HgCommand("/foo/foo", processBuilder, "hg", "log");

        String res = command.execute();

        assertEquals(expected, res);
    }

    @Test(expected = ExecuteException.class)
    public void execute_failed() throws ExecuteException, IOException {
        ProcessFactory processBuilder = Mockito.mock(ProcessFactory.class);
        IOException expectedException = new IOException("some io exception");
        when(processBuilder.start()).thenThrow(expectedException);
        HgCommand command = new HgCommand("/foo/foo", processBuilder, "hg", "log");

        command.execute();

        fail();
    }

    @Test
    public void toStringTest() {
        HgCommand command = new HgCommand("/foo/foo", "hg", "log");

        String actual = command.toString();

        String expected = "CurrentChangeset{repoPath='/foo/foo', command=[hg, log]}";
        assertEquals(expected, actual);
    }
}