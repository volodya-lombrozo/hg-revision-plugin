package domain.repo;

import util.exceptions.ExecuteException;

import java.util.List;

public interface Repository {

    List<String> bookmarks() throws ExecuteException;

    Changeset currentChangeset() throws ExecuteException;

    Changeset findChangeset(String rev) throws ExecuteException;

    String path();
}
