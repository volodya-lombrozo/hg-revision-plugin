package domain.command;

import util.exceptions.ExecuteException;

public interface Command {

    String execute() throws ExecuteException;


    class FakeCommand implements Command {
        @Override
        public String execute() {
            return "";
        }
    }
}
