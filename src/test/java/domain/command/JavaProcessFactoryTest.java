package domain.command;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JavaProcessFactoryTest {

    @Test
    public void start() throws IOException {
        ProcessBuilder delegate = new ProcessBuilder("java", "-version");
        JavaProcessFactory factory = new JavaProcessFactory(delegate);

        Process process = factory.start();

        assertNotNull(process);
        process.destroy();
    }

    @Test
    public void directory() {
        ProcessBuilder delegate = new ProcessBuilder();
        JavaProcessFactory factory = new JavaProcessFactory(delegate);
        File expectedFile = new File("/some/path");

        factory.directory(expectedFile);

        assertEquals(expectedFile, delegate.directory());
    }
}