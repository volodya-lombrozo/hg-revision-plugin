package domain.command;

public interface Command {

    String execute() throws ExecuteException;

}
