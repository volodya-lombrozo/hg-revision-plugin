package domain.repo;

import com.aragost.javahg.Bookmark;
import com.aragost.javahg.commands.BookmarksCommand;
import domain.command.ExecuteException;
import service.RepositorySteward;

import java.util.List;
import java.util.stream.Collectors;

public class JavaHgRepository implements Repository {

    private final com.aragost.javahg.Repository delegate;

    public JavaHgRepository(String repoPath) {
        this(new RepositorySteward(repoPath).openRepository());
    }

    public JavaHgRepository(com.aragost.javahg.Repository delegate) {
        this.delegate = delegate;
    }

    @Override
    public List<String> bookmarks() {
        return BookmarksCommand.on(delegate).list().stream().map(Bookmark::getName).collect(Collectors.toList());
    }

    @Override
    public Changeset currentChangeset() {
        com.aragost.javahg.Changeset changeset = delegate.workingCopy().getParent1();
        if (changeset == null) return null;
        return new JavaHgChangeset(changeset);
    }

    @Override
    public Changeset findChangeset(String rev) {
        throw new UnsupportedOperationException("Method 'findChangeset(String rev)' not supported by " + this);
    }

    @Override
    public String path() {
        return delegate.getDirectory().getPath();
    }

    @Override
    public String toString() {
        return "JavaHgRepository{" + "delegate=" + delegate + '}';
    }
}
