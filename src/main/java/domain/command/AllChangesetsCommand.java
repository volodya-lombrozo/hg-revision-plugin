package domain.command;

public class AllChangesetsCommand implements Command {
    private final Command delegate;

    public AllChangesetsCommand(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "--debug", "--template", "user:'{author}'\nbranch:'{branch}'\ndate:'{date|hgdate}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:'{parents}'\n\n"));
    }

    public AllChangesetsCommand(Command delegate) {
        this.delegate = delegate;
    }

    @Override
    public String execute() throws ExecuteException {
        return delegate.execute();
    }
}
