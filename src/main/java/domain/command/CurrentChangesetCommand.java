package domain.command;

public class CurrentChangesetCommand implements Command {

    private final Command delegate;

    public CurrentChangesetCommand(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "-r", ".", "--template", "\"user:'{author}'\nbranch:'{branch}'\ndate:'{date}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:{parents}\""));
    }

    public CurrentChangesetCommand(Command delegate) {
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
