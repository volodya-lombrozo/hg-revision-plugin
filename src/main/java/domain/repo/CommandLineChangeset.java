package domain.repo;

import java.time.Instant;
import java.util.List;

public class CommandLineChangeset implements Changeset {
    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getBranch() {
        return null;
    }

    @Override
    public String getFormattedDateTime() {
        return null;
    }

    @Override
    public Instant getDateTime() {
        return null;
    }

    @Override
    public Changeset getLeftParent() {
        return null;
    }

    @Override
    public Changeset getRightParent() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getNode() {
        return null;
    }

    @Override
    public List<String> tags() {
        return null;
    }

    @Override
    public String getRevision() {
        return null;
    }
}
