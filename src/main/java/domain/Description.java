package domain;

import com.aragost.javahg.Changeset;

import java.util.Properties;

public class Description implements RecordableProperty {

    private final Changeset changeset;

    public Description(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.put("hg.desc", this.toString());
    }

    @Override
    public String toString() {
        return changeset.getMessage();
    }
}
