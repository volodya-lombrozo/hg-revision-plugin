package domain.command;

import util.exceptions.ExecuteException;

public class CurrentChangesetCommand implements Command {

    private final Command delegate;

    public CurrentChangesetCommand(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "-r", ".", "--debug", "--template", "user:'{author}'\nbranch:'{branch}'\ndate:'{date|hgdate}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:'{parents}'\n"));
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
