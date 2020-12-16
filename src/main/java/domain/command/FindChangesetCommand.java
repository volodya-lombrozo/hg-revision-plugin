package domain.command;

public class FindChangesetCommand implements Command {

    private final Command delegate;

    public FindChangesetCommand(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "-r", ".", "--template", "user:'{author}'\nbranch:'{branch}'\ndate:'{date|isodate}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:'{parents}'"));
    }

    public FindChangesetCommand(Command delegate) {
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
