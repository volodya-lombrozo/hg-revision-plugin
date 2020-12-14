package domain.command;

import java.io.File;
import java.io.IOException;

public interface ProcessFactory {
    Process start() throws IOException;

    void directory(File file);
}
