package util;

import domain.repo.Changeset;

public class ChangesetParents {
    private final Changeset changeset;

    public ChangesetParents(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public String toString() {
        Changeset leftParent = changeset.getLeftParent();
        Changeset rightParent = changeset.getRightParent();
        if (leftParent == null && rightParent == null)
            return "";
        else if (rightParent == null)
            return leftParent.getRevision() + ":" + leftParent.getNode();
        else if (leftParent == null)
            return rightParent.getRevision() + ":" + rightParent.getNode();
        else
            return leftParent.getRevision() + ":" + leftParent.getNode() + " " + rightParent.getRevision() + ":" + rightParent.getNode();
    }
}
