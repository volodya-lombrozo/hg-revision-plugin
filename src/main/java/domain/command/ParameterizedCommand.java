package domain.command;

import util.exceptions.ExecuteException;

public interface ParameterizedCommand {

    String execute(String... params) throws ExecuteException;

    class Fake implements ParameterizedCommand {
        @Override
        public String execute(String... params) {
            return "";
        }
    }
}
