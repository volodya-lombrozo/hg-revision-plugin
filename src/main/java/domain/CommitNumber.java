package domain;

import com.aragost.javahg.Changeset;

import java.util.Properties;

public class CommitNumber implements RecordableProperty {

    private final Changeset changeset;

    public CommitNumber(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        int commitNumber = commitNumber(changeset, changeset.getBranch());
        properties.put("hg.commit.number", String.valueOf(commitNumber));
    }

    private int commitNumber(Changeset changeset, String branch) {
        if (changeset == null || !branch.equals(changeset.getBranch()))
            return 0;
        else return 1 + commitNumber(changeset.getParent1(), branch) + commitNumber(changeset.getParent2(), branch);
    }


}
