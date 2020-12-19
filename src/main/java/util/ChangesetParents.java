package util;

import domain.repo.Changeset;

public class ChangesetParents {
    public static final String EMPTY_PARENT = "-1:000000000000000000";
    private final Changeset changeset;

    public ChangesetParents(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public String toString() {
        Changeset leftParent = changeset.getLeftParent();
        Changeset rightParent = changeset.getRightParent();
        if (leftParent == null && rightParent == null)
            return EMPTY_PARENT + " " + EMPTY_PARENT;
        else if (rightParent == null)
            return leftParent.getRevision() + ":" + leftParent.getNode() + " " + EMPTY_PARENT;
        else if (leftParent == null)
            return EMPTY_PARENT + " " + rightParent.getRevision() + ":" + rightParent.getNode();
        else
            return leftParent.getRevision() + ":" + leftParent.getNode() + " " + rightParent.getRevision() + ":" + rightParent.getNode();
    }
}
