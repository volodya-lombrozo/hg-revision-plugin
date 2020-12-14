package domain.command;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class HgCommand implements Command {
    private final String repoPath;
    private final String[] command;

    public HgCommand(String repoPath, String... command) {
        this.repoPath = repoPath;
        this.command = command;
    }

    @Override
    public String execute() throws ExecuteException {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.directory(Paths.get(repoPath).toFile());
            Process process = processBuilder.start();
            String output = new StreamedString(process.getInputStream()).read();
            int exitCode = process.waitFor();
            if (exitCode != 0) throw new ExecuteException(new StreamedString(process.getErrorStream()).read());
            return output;
        } catch (IOException | InterruptedException e) {
            throw new ExecuteException("Fail during execution of command: " + this, e);
        }
    }

    @Override
    public String toString() {
        return "CurrentChangeset{" +
                "repoPath='" + repoPath + '\'' +
                ", command=" + Arrays.toString(command) +
                '}';
    }
}
