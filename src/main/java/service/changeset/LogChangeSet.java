package service.changeset;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.LogCommand;
import service.exceptions.ChangesetNotFound;

public class LogChangeSet implements ChangesetAdapter {

    private final Repository repository;

    public LogChangeSet(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Changeset toChangeSet() throws ChangesetNotFound {
        LogCommand logCommand = LogCommand.on(repository);
        return logCommand.execute().stream()
                .findFirst().orElseThrow(ChangesetNotFound::new);
    }
}
