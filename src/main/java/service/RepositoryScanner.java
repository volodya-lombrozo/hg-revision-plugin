package service;

import com.aragost.javahg.Bookmark;
import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.commands.BookmarksCommand;
import domain.RepoInfo;
import domain.RepoInfoBuilder;
import service.changeset.ChangesetAdapter;
import service.changeset.CurrentChangeSet;
import service.exceptions.ChangesetNotFound;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryScanner {

    public RepoInfo scan(String baseDir) throws ChangesetNotFound {
        Repository repository = Repository.open(new File(baseDir));
        ChangesetAdapter adapter = new CurrentChangeSet(repository);
        Changeset changeset = adapter.toChangeSet();
        List<String> bookmarks = BookmarksCommand.on(repository).list().stream().map(Bookmark::getName).collect(Collectors.toList());
        return new RepoInfoBuilder()
                .setHgBranch(changeset.getBranch())
                .setHgRevision(String.valueOf(changeset.getRevision()))
                .setHgRevisionHash(changeset.getNode())
                .setHgDescription(changeset.getMessage())
                .setHgDate(changeset.getTimestamp().getDate())
                .setHgAuthor(changeset.getUser())
                .setHgTags(changeset.tags())
                .setBookmarks(bookmarks)
                .create();
    }

}
