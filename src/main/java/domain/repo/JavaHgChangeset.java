package domain.repo;

import com.aragost.javahg.DateTime;
import util.FormattedDateTime;

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
    public String getDateTime() {
        DateTime timestamp = delegate.getTimestamp();
        if (timestamp == null) return "";
        else return new FormattedDateTime(timestamp.getDate()).toString();
    }

    @Override
    public Changeset getLeftParent() {
        return new JavaHgChangeset(delegate.getParent1());
    }

    @Override
    public Changeset getRightParent() {
        return new JavaHgChangeset(delegate.getParent2());
    }
}
