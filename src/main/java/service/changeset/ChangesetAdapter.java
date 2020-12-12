package service.changeset;

import domain.repo.Changeset;
import service.exceptions.ChangesetNotFound;

public interface ChangesetAdapter {
    Changeset toChangeSet() throws ChangesetNotFound;
}
