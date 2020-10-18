package service.exceptions;

public class ChangesetNotFound extends Exception{

    public ChangesetNotFound() {
        super("Any changeset not found");
    }
}
