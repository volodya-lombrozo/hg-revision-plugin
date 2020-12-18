package domain.repo;

import domain.command.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineRepository implements Repository {

    private final Command currentChangesetCommand;
    private final Command bookmarksCommand;
    private final ParameterizedCommand findChangesetCommand;
    private final String path;

    public CommandLineRepository(String repoPath) {
        this(new CurrentChangesetCommand(repoPath), new AllRepositoryBookmarksCommand(repoPath), new FindChangesetCommand(repoPath), repoPath);
    }

    public CommandLineRepository(Command changesetCommand, Command bookmarksCommand, ParameterizedCommand findChangesetCommand) {
        this(changesetCommand, bookmarksCommand, findChangesetCommand, "");
    }

    public CommandLineRepository(Command changesetCommand, Command bookmarksCommand, ParameterizedCommand findChangesetCommand, String path) {
        this.currentChangesetCommand = changesetCommand;
        this.bookmarksCommand = bookmarksCommand;
        this.findChangesetCommand = findChangesetCommand;
        this.path = path;
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
        if (rev.isEmpty()) return null; //fixme: it's really ugly solution to return null.
        return new CommandLineChangeset(findChangesetCommand.execute(rev), this);
    }

    @Override
    public String path() {
        return path;
    }

    public boolean notEmptyCommands() {
        return currentChangesetCommand != null && bookmarksCommand != null && findChangesetCommand != null;
    }
}
