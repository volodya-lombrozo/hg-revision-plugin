package domain;

import domain.repo.Repository;

import java.util.List;
import java.util.Properties;

public class Bookmarks implements RecordableProperty {

    private final Repository repository;

    public Bookmarks(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.setProperty("hg.bookmarks", this.toString());
    }

    @Override
    public String toString() {
        List<String> bookmarks = repository.bookmarks();
        return String.join(";", bookmarks);
    }
}
