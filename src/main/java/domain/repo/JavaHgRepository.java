package domain.repo;

import com.aragost.javahg.Bookmark;
import com.aragost.javahg.commands.BookmarksCommand;

import java.util.List;
import java.util.stream.Collectors;

public class JavaHgRepository implements Repository {

    private final com.aragost.javahg.Repository delegate;

    public JavaHgRepository(com.aragost.javahg.Repository delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<String> bookmarks() {
        return BookmarksCommand.on(delegate).list().stream().map(Bookmark::getName).collect(Collectors.toList());
    }

    @Override
    public Changeset currentChangeset() {
        return new JavaHgChangeset(delegate.workingCopy().getParent1());
    }
}
