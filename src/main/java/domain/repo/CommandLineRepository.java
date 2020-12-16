package domain.repo;

import domain.command.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineRepository implements Repository {

    private final Command currentChangesetCommand;
    private final Command bookmarksCommand;
    private final ParameterizedCommand findChangesetCommand;

    public CommandLineRepository(String repoPath) {
        this(new CurrentChangesetCommand(repoPath), new AllRepositoryBookmarksCommand(repoPath), new FindChangesetCommand(repoPath));
    }

    public CommandLineRepository(Command changesetCommand, Command bookmarksCommand, ParameterizedCommand findChangesetCommand) {
        this.currentChangesetCommand = changesetCommand;
        this.bookmarksCommand = bookmarksCommand;
        this.findChangesetCommand = findChangesetCommand;
    }

    @Override
    public List<String> bookmarks() throws ExecuteException {
        String bookmarksArray = bookmarksCommand.execute();
        String[] split = bookmarksArray.split("\n");
        return Arrays.stream(split).map(String::trim).collect(Collectors.toList());
    }

    @Override
    public Changeset currentChangeset() throws ExecuteException {
        String output = currentChangesetCommand.execute();
        return new CommandLineChangeset(output, this);
    }

    @Override
    public Changeset findChangeset(String rev) throws ExecuteException {
        return new CommandLineChangeset(findChangesetCommand.execute());
    }

    public boolean notEmptyCommands() {
        return currentChangesetCommand != null && bookmarksCommand != null;
    }
}
