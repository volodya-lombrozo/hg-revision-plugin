package service.changeset;

import com.aragost.javahg.Changeset;
import service.exceptions.ChangesetNotFound;

public interface ChangesetAdapter {
    Changeset toChangeSet() throws ChangesetNotFound;
}
