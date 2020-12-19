package domain.command;

import util.exceptions.ExecuteException;

import java.util.function.Function;

public class FindChangesetCommand implements ParameterizedCommand {

    private final Function<String, Command> commandFactory;

    public FindChangesetCommand(String repoPath) {
        this(rev -> new HgCommand(repoPath, "hg", "log", "-r", rev, "--debug", "--template", "user:'{author}'\nbranch:'{branch}'\ndate:'{date|hgdate}'\nmessage:'{desc}'\nnode:'{node}'\nrev:'{rev}'\ntags:'{tags}'\nparents:'{parents}'"));
    }

    public FindChangesetCommand(Function<String, Command> commandFactory) {
        this.commandFactory = commandFactory;
    }

    @Override
    public String execute(String... params) throws ExecuteException {
        if (params == null || params.length == 0)
            throw new ExecuteException("For command " + this + " you should pass one param: revision");
        String rev = params[0];
        Command command = commandFactory.apply(rev);
        return command.execute();
    }

    public boolean commandFactoryIsInstalled() {
        return commandFactory != null;
    }

    @Override
    public String toString() {
        return "FindChangesetCommand{" +
                "commandFactory=" + commandFactory +
                '}';
    }
}
