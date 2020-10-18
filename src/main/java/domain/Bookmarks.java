package domain;

import com.aragost.javahg.Repository;
import domain.util.BookmarksExtractor;
import domain.util.CommandExtractor;

import java.util.List;
import java.util.Properties;

public class Bookmarks implements RecordableProperty {

    private final Repository repository;
    private final BookmarksExtractor extractor;

    public Bookmarks(Repository repository) {
        this(repository, new CommandExtractor(repository));
    }

    public Bookmarks(Repository repository, BookmarksExtractor extractor) {
        this.repository = repository;
        this.extractor = extractor;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.setProperty("hg.bookmarks", this.toString());
    }

    @Override
    public String toString() {
        List<String> bookmarks = extractor.extract();
        return String.join(";", bookmarks);
    }
}
