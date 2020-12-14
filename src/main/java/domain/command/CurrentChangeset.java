package domain.command;

/*
 * Command for getting of current changeset
 * Tested on Windows OS only
 *
 * hg log -r . --template "user:{author}\nbranch:{branch}\ndate:{date}\nmessage:{desc}\nnode:{node}\nrev:{rev}\ntags:{tags}\nparents:{parents}"
 *
 */
public class CurrentChangeset implements Command {

    private final HgCommand delegate;

    public CurrentChangeset(String repoPath) {
        this(new HgCommand(repoPath, "hg", "log", "-r", ".", "--template", "\"user:'{author}'\nbranch:'{branch}'\ndate:'{date}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:{parents}\""));
    }

    public CurrentChangeset(HgCommand delegate) {
        this.delegate = delegate;
    }

    @Override
    public String execute() throws ExecuteException {
        return delegate.execute();
    }
}
