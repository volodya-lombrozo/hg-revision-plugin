package domain;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import domain.repo.JavaHgChangeset;
import domain.repo.JavaHgRepository;
import service.changeset.CurrentChangeSet;
import service.exceptions.ChangesetNotFound;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RepositoryInfo {

    private final Repository repository;

    public RepositoryInfo(Repository repository) {
        this.repository = repository;
    }

    public void fillProperties(Properties properties) {
        setWarningLogLevel();
        allRepoProperties().forEach(item -> item.fillProperties(properties));
    }

    private void setWarningLogLevel() {
        Logger logger = Logger.getLogger("com.aragost.javahg.internals.Server");
        logger.setLevel(Level.WARNING);
    }

    private List<RecordableProperty> allRepoProperties() {
        Changeset currentCommit = new CurrentChangeSet(repository).toChangeSet();
        RecordableProperty author = new Author(new JavaHgChangeset(currentCommit));
        RecordableProperty branch = new Branch(currentCommit);
        RecordableProperty commitDate = new CommitDate(currentCommit);
        RecordableProperty description = new Description(currentCommit);
        RecordableProperty node = new Node(currentCommit);
        RecordableProperty revision = new Revision(currentCommit);
        RecordableProperty tags = new Tags(currentCommit);
        RecordableProperty bookmarks = new Bookmarks(new JavaHgRepository(repository));
        RecordableProperty previousTags = new PreviousTags(currentCommit);
        RecordableProperty commitNumber = new CommitNumber(currentCommit);
        return asLoggableProperties(author, branch, commitDate, description, node,
                revision, tags, bookmarks
                , previousTags
                , commitNumber
        );
    }

    private List<RecordableProperty> asLoggableProperties(RecordableProperty... properties) {
        return Arrays.stream(properties).map(Loggable::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        allRepoProperties().forEach(item -> sb.append(item.getClass().getName())
                .append(":")
                .append(item.toString())
                .append("\n"));
        return sb.toString();
    }
}
