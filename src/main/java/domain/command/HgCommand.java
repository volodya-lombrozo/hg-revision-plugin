package domain.command;

import util.InputString;
import util.exceptions.ExecuteException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class HgCommand implements Command {
    private final String repoPath;
    private final String[] command;
    private final ProcessFactory processBuilder;

    public HgCommand(String repoPath, String... command) {
        this(repoPath, new JavaProcessFactory(command), command);
    }

    public HgCommand(String repoPath, ProcessFactory processBuilder, String... command) {
        this.repoPath = repoPath;
        this.command = command;
        this.processBuilder = processBuilder;
    }

    @Override
    public String execute() throws ExecuteException {
        try {
            File file = Paths.get(repoPath).toFile();
            processBuilder.directory(file);
            Process process = processBuilder.start();
            String output = new InputString(process.getInputStream()).read();
            int exitCode = process.waitFor();
            if (exitCode != 0) throw new ExecuteException(new InputString(process.getErrorStream()).read());
            return output;
        } catch (IOException | InterruptedException e) {
            throw new ExecuteException("Fail during execution of command: " + this, e);
        }
    }

    @Override
    public String toString() {
        return "CurrentChangeset{" +
                "repoPath='" + repoPath + '\'' +
                ", command=[" + String.join(" ", command) + "]" +
                '}';
    }
}
