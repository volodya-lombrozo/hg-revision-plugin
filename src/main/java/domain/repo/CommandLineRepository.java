package domain.repo;

import domain.command.CurrentChangeset;
import domain.command.ExecuteException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommandLineRepository implements Repository {

    private final String repoPath;

    public CommandLineRepository(String repoPath) {
        this.repoPath = repoPath;
    }

    @Override
    public List<String> bookmarks() {
        return null;
    }

    @Override
    public Changeset currentChangeset() {
        try {
            String output = new CurrentChangeset(repoPath).execute();
            return new CommandLineChangeset(output);
        } catch (ExecuteException e) {
            e.printStackTrace();
            return CommandLineChangeset.undefined();
        }
    }
}
