package util;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.DateTime;

import java.time.Instant;

public class ChangesetTime {

    private final Changeset changeset;

    public ChangesetTime(Changeset changeset) {
        this.changeset = changeset;
    }

    public Instant toInstant() {
        if (changeset == null || changeset.getTimestamp() == null || changeset.getTimestamp().getDate() == null)
            return Instant.MIN;
        else return changeset.getTimestamp().getDate().toInstant();
    }

}
