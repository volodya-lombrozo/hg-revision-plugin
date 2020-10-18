package domain.util;

import com.aragost.javahg.Bookmark;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.BookmarksCommand;

import java.util.List;
import java.util.stream.Collectors;

public class CommandExtractor implements BookmarksExtractor {

    private final Repository repository;

    public CommandExtractor(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> extract() {
        return BookmarksCommand.on(repository).list().stream().map(Bookmark::getName).collect(Collectors.toList());
    }
}
