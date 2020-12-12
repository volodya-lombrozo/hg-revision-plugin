package service.changeset;


import domain.repo.Changeset;
import domain.repo.Repository;

public class CurrentChangeSet implements ChangesetAdapter {

    private final Repository repository;

    public CurrentChangeSet(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Changeset toChangeSet() {
        return repository.currentChangeset();
    }


}
