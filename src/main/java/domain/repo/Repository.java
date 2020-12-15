package domain.repo;

import domain.command.ExecuteException;

import java.util.List;

public interface Repository {

    List<String> bookmarks() throws ExecuteException;

    Changeset currentChangeset() throws ExecuteException;
}
