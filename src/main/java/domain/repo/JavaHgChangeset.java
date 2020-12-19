package domain.repo;

import com.aragost.javahg.DateTime;
import util.FormattedDateTime;
import util.MinZonedDateTime;
import util.OldZonedDateTime;

import java.time.ZonedDateTime;
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
    public ZonedDateTime getZonedDateTime() {
        DateTime timestamp = delegate.getTimestamp();
        if (timestamp == null) return new MinZonedDateTime().min();
        Date date = timestamp.getDate();
        if (date == null) return new MinZonedDateTime().min();
        return new OldZonedDateTime(date).toZonedDateTime();
    }

    @Override
    public Changeset getLeftParent() {
        com.aragost.javahg.Changeset parent1 = delegate.getParent1();
        if (parent1 == null) return null;
        return new JavaHgChangeset(parent1);
    }

    @Override
    public Changeset getRightParent() {
        com.aragost.javahg.Changeset parent2 = delegate.getParent2();
        if (parent2 == null) return null;
        return new JavaHgChangeset(parent2);
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

    @Override
    public String getRevision() {
        return String.valueOf(delegate.getRevision());
    }
}
