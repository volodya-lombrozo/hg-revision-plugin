package service.changeset;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import domain.Bookmarks;
import domain.RepoInfo;
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

    public RepoInfo toRepoInfo() throws ChangesetNotFound {
        return new RepoInfo(toChangeSet(), new Bookmarks(repository).toString());
    }
}
