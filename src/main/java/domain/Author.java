package domain;

import domain.repo.Changeset;

import java.util.Properties;

public class Author implements RecordableProperty {

    private final Changeset changeset;

    public Author(Changeset changeset) {
        this.changeset = changeset;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.setProperty("hg.author", this.toString());
    }

    @Override
    public String toString() {
        return changeset.getUser();
    }
}
