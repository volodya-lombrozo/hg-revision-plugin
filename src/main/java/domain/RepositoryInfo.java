package domain;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import service.changeset.CurrentChangeSet;
import service.exceptions.ChangesetNotFound;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class RepositoryInfo {

    private final Repository repository;

    public RepositoryInfo(Repository repository) {
        this.repository = repository;
    }

    public void fillProperties(Properties properties) throws ChangesetNotFound {
        allRepoProperties().forEach(item -> item.fillProperties(properties));
    }

    private List<RecordableProperty> allRepoProperties() throws ChangesetNotFound {
        Changeset currentCommit = new CurrentChangeSet(repository).toChangeSet();
        RecordableProperty author = new Author(currentCommit);
        RecordableProperty branch = new Branch(currentCommit);
        RecordableProperty commitDate = new CommitDate(currentCommit);
        RecordableProperty description = new Description(currentCommit);
        RecordableProperty node = new Node(currentCommit);
        RecordableProperty revision = new Revision(currentCommit);
        RecordableProperty tags = new Tags(currentCommit);
        RecordableProperty bookmarks = new Bookmarks(repository);
        RecordableProperty previousTags = new PreviousTags(currentCommit);
        return Arrays.asList(author, branch, commitDate, description, node, revision, tags, bookmarks, previousTags);
    }

    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            allRepoProperties().forEach(item -> sb.append(item.getClass().getName())
                    .append(":")
                    .append(item.toString())
                    .append("\n"));
            return sb.toString();
        } catch (ChangesetNotFound changesetNotFound) {
            return changesetNotFound.getMessage();
        }
    }
}
