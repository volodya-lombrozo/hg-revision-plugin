package domain.repo;

import java.time.ZonedDateTime;
import java.util.List;

public interface Changeset {

    String getUser();

    String getBranch();

    String getFormattedDateTime();

    ZonedDateTime getZonedDateTime();

    Changeset getLeftParent();

    Changeset getRightParent();

    String getMessage();

    String getNode();

    List<String> tags();

    String getRevision();
}
