package domain.repo;

import com.aragost.javahg.DateTime;
import util.FormattedDateTime;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class JavaHgChangeset implements Changeset {

    private final com.aragost.javahg.Changeset delegate;

    public JavaHgChangeset(com.aragost.javahg.Changeset delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getUser() {
        return delegate.getUser();
    }

    @Override
    public String getBranch() {
        return delegate.getBranch();
    }

    @Override
    public String getFormattedDateTime() {
        DateTime timestamp = delegate.getTimestamp();
        if (timestamp == null) return "";
        else return new FormattedDateTime(timestamp.getDate()).toString();
    }

    @Override
    public Instant getDateTime() {
        DateTime timestamp = delegate.getTimestamp();
        if (timestamp == null) return Instant.MIN;
        Date date = timestamp.getDate();
        if (date == null) return Instant.MIN;
        return date.toInstant();
    }

    @Override
    public Changeset getLeftParent() {
        return new JavaHgChangeset(delegate.getParent1());
    }

    @Override
    public Changeset getRightParent() {
        return new JavaHgChangeset(delegate.getParent2());
    }

    @Override
    public String getMessage() {
        return delegate.getMessage();
    }

    @Override
    public String getNode() {
        return delegate.getNode();
    }

    @Override
    public List<String> tags() {
        return delegate.tags();
    }
}
