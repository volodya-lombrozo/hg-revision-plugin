package util;

import domain.repo.Changeset;

public class HgChangesetString {

    private final Changeset changeset;

    public HgChangesetString(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public String toString() {
        return "user:'" + changeset.getUser() + "'\nbranch:'" + changeset.getBranch() + "'\ndate:'"
                + new HgDateTimeString(changeset.getZonedDateTime()).toString() + "'\nmessage:'" + changeset.getMessage()
                + "'\nnode:'" + changeset.getNode()
                + "'\ntags:'" + String.join(" ", changeset.tags())
                + "'\nrevision:'" + changeset.getRevision()
                + "'\nparents:'{parents}'";
    }
}
