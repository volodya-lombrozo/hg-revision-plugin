package domain.repo;

import java.util.List;

public class FakeRepository implements Repository {
    @Override
    public List<String> bookmarks() {
        throw new UnsupportedOperationException("Operation 'bookmarks()' not supported by " + this);
    }

    @Override
    public Changeset currentChangeset() {
        throw new UnsupportedOperationException("Operation 'currentChangeset()' not supported by " + this);
    }

    @Override
    public Changeset findChangeset(String rev) {
        throw new UnsupportedOperationException("Operation 'findChangeset()' not supported by " + this);
    }

    @Override
    public String toString() {
        return "FakeRepository#" + hashCode();
    }
}
