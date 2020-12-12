package util;


import domain.repo.Changeset;

import java.util.ArrayList;
import java.util.List;

public class ChangesetTags {

    private final Changeset changeset;

    public ChangesetTags(Changeset changeset) {
        this.changeset = changeset;
    }

    public List<String> toTags() {
        if (changeset == null || changeset.tags() == null)
            return new ArrayList<>(0);
        else return changeset.tags();
    }
}
