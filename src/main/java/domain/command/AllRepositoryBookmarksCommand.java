package domain.command;

public class AllRepositoryBookmarksCommand implements Command {

    private final Command delegate;

    public AllRepositoryBookmarksCommand(String repoPath) {
        this(new HgCommand(repoPath, "hg", "bookmarks", "--template", "{bookmarks}\n"));
    }

    public AllRepositoryBookmarksCommand(Command delegate) {
        this.delegate = delegate;
    }

    @Override
    public String execute() throws ExecuteException {
        return delegate.execute();
    }

    public boolean isNotNullDelegate() {
        return delegate != null;
    }
}
