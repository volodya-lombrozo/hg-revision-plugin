package domain;

import com.aragost.javahg.Changeset;

import java.util.Properties;

public class Revision implements RecordableProperty {

    private final Changeset changeset;

    public Revision(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.rev", this.toString());
    }

    @Override
    public String toString() {
        return String.valueOf(changeset.getRevision());
    }
}
