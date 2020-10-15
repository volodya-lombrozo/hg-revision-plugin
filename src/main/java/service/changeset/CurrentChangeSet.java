package service.changeset;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import service.exceptions.ChangesetNotFound;

public class CurrentChangeSet implements ChangesetAdapter {

    private final Repository repository;

    public CurrentChangeSet(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Changeset toChangeSet() throws ChangesetNotFound {
        return repository.workingCopy().getParent1();
    }
}
