package domain.command;

public class CurrentChangeset implements Command {

    private final Command delegate;

    public CurrentChangeset(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "-r", ".", "--template", "\"user:'{author}'\nbranch:'{branch}'\ndate:'{date}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:{parents}\""));
    }

    public CurrentChangeset(Command delegate) {
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
