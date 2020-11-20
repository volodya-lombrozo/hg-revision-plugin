package domain;

import com.aragost.javahg.Changeset;

import java.util.HashSet;
import java.util.Properties;

public class CommitNumber implements RecordableProperty {

    private final Changeset changeset;
    private final HashSet<Changeset> alreadyVisited = new HashSet<>();

    public CommitNumber(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        int commitNumber = commitNumber(changeset, changeset.getBranch());
        properties.put("hg.commit.number", String.valueOf(commitNumber));
    }

    private int commitNumber(Changeset changeset, String branch) {
        if (changeset == null || !branch.equals(changeset.getBranch()) || alreadyVisited.contains(changeset))
            return 0;
        else {
            alreadyVisited.add(changeset);
            return 1 + commitNumber(changeset.getParent1(), branch) + commitNumber(changeset.getParent2(), branch);
        }
    }


}
