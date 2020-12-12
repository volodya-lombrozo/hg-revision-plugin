package domain.repo;

public interface Changeset {

    String getUser();

    String getBranch();

    String getDateTime();

    Changeset getLeftParent();

    Changeset getRightParent();
}
