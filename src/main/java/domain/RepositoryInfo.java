package domain;

import domain.repo.Repository;
import domain.repo.Changeset;

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
        Changeset changeset = repository.currentChangeset();
        RecordableProperty author = new Author(changeset);
        RecordableProperty branch = new Branch(changeset);
        RecordableProperty commitDate = new CommitDate(changeset);
        RecordableProperty description = new Description(changeset);
        RecordableProperty node = new Node(changeset);
        RecordableProperty revision = new Revision(changeset);
        RecordableProperty tags = new Tags(changeset);
        RecordableProperty bookmarks = new Bookmarks(repository);
        RecordableProperty previousTags = new PreviousTags(changeset);
        RecordableProperty commitNumber = new CommitNumber(changeset);
        return asLoggableProperties(author, branch, commitDate, description, node,
                revision, tags, bookmarks, previousTags, commitNumber
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
