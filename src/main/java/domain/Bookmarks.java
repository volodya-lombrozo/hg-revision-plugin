package domain;

import com.aragost.javahg.Bookmark;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.BookmarksCommand;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Bookmarks implements RecordableProperty {

    private final Repository repository;

    public Bookmarks(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void fillProperties(Properties properties) {
        properties.setProperty("hg.bookmarks",this.toString());
    }

    @Override
    public String toString() {
        List<String> bookmarks = BookmarksCommand.on(repository).list().stream().map(Bookmark::getName).collect(Collectors.toList());
        return String.join(";", bookmarks);
    }
}
