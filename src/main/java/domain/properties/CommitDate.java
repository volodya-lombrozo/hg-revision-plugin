package domain.properties;


import domain.repo.Changeset;

import java.util.Properties;

public class CommitDate implements RecordableProperty {

    private final Changeset changeset;

    public CommitDate(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.date", this.toString());
    }

    @Override
    public String toString() {
        return changeset.getFormattedDateTime();
    }
}
