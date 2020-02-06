package service.exceptions;

public class ChangesetNotFound extends Exception{

    public ChangesetNotFound() {
        super("Changesets not found");
    }
}
