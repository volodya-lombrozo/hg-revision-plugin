package domain.repo;

import java.time.Instant;
import java.util.List;

public interface Changeset {

    String getUser();

    String getBranch();

    String getFormattedDateTime();

    Instant getDateTime();

    Changeset getLeftParent();

    Changeset getRightParent();

    String getMessage();

    String getNode();

    List<String> tags();
}
