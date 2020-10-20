package service.changeset;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.*;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentChangeSet implements ChangesetAdapter {

    private final Repository repository;

    public CurrentChangeSet(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Changeset toChangeSet() {
        return repository.workingCopy().getParent1();
    }


}
