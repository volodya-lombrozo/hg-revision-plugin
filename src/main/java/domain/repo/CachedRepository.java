package domain.repo;

import domain.command.AllChangesetsCommand;
import domain.command.Command;
import util.exceptions.ExecuteException;
import util.StringChangesetCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CachedRepository implements Repository {

    private final Repository delegate;
    private final List<String> cachedBookmarks;
    private final Map<String, Changeset> changesetMap;
    private final AtomicReference<Changeset> currentChangeset;
    private final Command findAllChangesetCommand;

    public CachedRepository(Repository delegate) {
        this(delegate, new ArrayList<>(), new HashMap<>(), new AtomicReference<>());
    }

    public CachedRepository(Repository delegate, List<String> cachedBookmarks) {
        this(delegate, cachedBookmarks, new HashMap<>(), new AtomicReference<>());
    }

    public CachedRepository(Repository delegate, Map<String, Changeset> changesetMap) {
        this(delegate, new ArrayList<>(), changesetMap, new AtomicReference<>());
    }

    public CachedRepository(Repository delegate, AtomicReference<Changeset> currentChangeset) {
        this(delegate, new ArrayList<>(), new HashMap<>(), currentChangeset);
    }


    public CachedRepository(Repository delegate, List<String> cachedBookmarks, Map<String, Changeset> changesetMap, AtomicReference<Changeset> currentChangeset) {
        this(delegate, cachedBookmarks, changesetMap, currentChangeset, new AllChangesetsCommand(delegate.path()));
    }

    public CachedRepository(Repository delegate, Command findAllChangesetCommand) {
        this(delegate, new ArrayList<>(), new HashMap<>(), new AtomicReference<>(), findAllChangesetCommand);
    }

    public CachedRepository(Repository delegate, List<String> cachedBookmarks, Map<String, Changeset> changesetMap, AtomicReference<Changeset> currentChangeset, Command findAllChangesetCommand) {
        this.delegate = delegate;
        this.cachedBookmarks = cachedBookmarks;
        this.changesetMap = changesetMap;
        this.currentChangeset = currentChangeset;
        this.findAllChangesetCommand = findAllChangesetCommand;
    }

    @Override
    public synchronized List<String> bookmarks() throws ExecuteException {
        if (cachedBookmarks.isEmpty())
            cachedBookmarks.addAll(delegate.bookmarks());
        return cachedBookmarks;
    }

    @Override
    public synchronized Changeset currentChangeset() throws ExecuteException {
        if (currentChangeset.get() == null)
            currentChangeset.set(new CommandLineChangeset(delegate.currentChangeset(), this));
        return currentChangeset.get();
    }

    @Override
    public synchronized Changeset findChangeset(String rev) throws ExecuteException {
        if (changesetMap.isEmpty())
            changesetMap.putAll(findAllChangesets());
        return changesetMap.get(rev);
    }

    @Override
    public String path() {
        return delegate.path();
    }

    private Map<String, Changeset> findAllChangesets() throws ExecuteException {
        return new StringChangesetCollection(findAllChangesetCommand.execute()).toMap(this);
    }


}
