package util;


import domain.repo.Changeset;

import java.time.Instant;
import java.time.ZonedDateTime;

public class ChangesetTime {

    private final Changeset changeset;

    public ChangesetTime(Changeset changeset) {
        this.changeset = changeset;
    }

    public ZonedDateTime toZonedDateTime() {
        if (changeset == null || changeset.getZonedDateTime() == null)
            return new MinZonedDateTime().min();
        else return changeset.getZonedDateTime();
    }

}
