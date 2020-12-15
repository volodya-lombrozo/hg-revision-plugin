package domain.command;

import java.io.File;
import java.io.IOException;

public class JavaProcessFactory implements ProcessFactory {
    private final ProcessBuilder delegate;

    public JavaProcessFactory(String[] command) {
        this(new ProcessBuilder(command));
    }

    public JavaProcessFactory(ProcessBuilder delegate) {
        this.delegate = delegate;
    }


    @Override
    public Process start() throws IOException {
        return delegate.start();
    }

    @Override
    public void directory(File file) {
        delegate.directory(file);
    }
}
