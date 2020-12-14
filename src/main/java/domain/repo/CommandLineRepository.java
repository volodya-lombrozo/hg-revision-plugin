package domain.repo;

import domain.command.Command;
import domain.command.CurrentChangesetCommand;
import domain.command.ExecuteException;

import java.util.Collections;
import java.util.List;

public class CommandLineRepository implements Repository {

    private final Command changesetCommand;
    private final Command bookmarksCommand;

    public CommandLineRepository(String repoPath) {
        this(new CurrentChangesetCommand(repoPath), null);
    }

    public CommandLineRepository(Command changesetCommand, Command bookmarksCommand) {
        this.changesetCommand = changesetCommand;
        this.bookmarksCommand = bookmarksCommand;
    }

    @Override
    public List<String> bookmarks() throws ExecuteException {
        return Collections.singletonList(bookmarksCommand.execute());
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
