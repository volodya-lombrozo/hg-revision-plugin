package domain;


import domain.repo.Changeset;

import java.util.Properties;

public class Branch implements RecordableProperty {

    private final Changeset changeset;

    public Branch(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.branch", this.toString());
    }

    @Override
    public String toString() {
        return changeset.getBranch();
    }
}
