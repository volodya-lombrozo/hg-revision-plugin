package domain.repo;

import domain.command.AllRepositoryBookmarksCommand;
import domain.command.Command;
import domain.command.CurrentChangesetCommand;
import domain.command.ExecuteException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineRepository implements Repository {

    private final Command changesetCommand;
    private final Command bookmarksCommand;

    public CommandLineRepository(String repoPath) {
        this(new CurrentChangesetCommand(repoPath), new AllRepositoryBookmarksCommand(repoPath));
    }

    public CommandLineRepository(Command changesetCommand, Command bookmarksCommand) {
        this.changesetCommand = changesetCommand;
        this.bookmarksCommand = bookmarksCommand;
    }

    @Override
    public List<String> bookmarks() throws ExecuteException {
        String bookmarksArray = bookmarksCommand.execute();
        String[] split = bookmarksArray.split("\n");
        return Arrays.stream(split).map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Changeset currentChangeset() {
        try {
            String output = changesetCommand.execute();
            return new CommandLineChangeset(output);
        } catch (ExecuteException e) {
            e.printStackTrace();
            return CommandLineChangeset.undefined();
        }
    }
}
