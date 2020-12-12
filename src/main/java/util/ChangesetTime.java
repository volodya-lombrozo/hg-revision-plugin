package util;


import domain.repo.Changeset;

import java.time.Instant;

public class ChangesetTime {

    private final Changeset changeset;

    public ChangesetTime(Changeset changeset) {
        this.changeset = changeset;
    }

    public Instant toInstant() {
        if (changeset == null || changeset.getDateTime() == null)
            return Instant.MIN;
        else return changeset.getDateTime();
    }

}
