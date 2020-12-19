package util;

import domain.repo.Changeset;
import util.time.ZonedHgTime;

public class FormattedChangeset {

    private final Changeset changeset;

    public FormattedChangeset(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public String toString() {
        return "user:'" + changeset.getUser()
                + "'\nbranch:'" + changeset.getBranch()
                + "'\ndate:'" + new ZonedHgTime(changeset.getZonedDateTime()).toString()
                + "'\nmessage:'" + changeset.getMessage()
                + "'\nnode:'" + changeset.getNode()
                + "'\ntags:'" + String.join(" ", changeset.tags())
                + "'\nrevision:'" + changeset.getRevision()
                + "'\nparents:'" + new ChangesetParents(changeset).toString() + "'";
    }
}
