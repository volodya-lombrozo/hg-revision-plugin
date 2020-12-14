package domain.repo;

import domain.command.OutputProperties;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class CommandLineChangeset implements Changeset {

    public static CommandLineChangeset undefined() {
        return new Undefined();
    }

    private final String commandOutput;

    public CommandLineChangeset(String commandOutput) {
        this.commandOutput = commandOutput;
    }

    @Override
    public String getUser() {
        return new OutputProperties(commandOutput).property("user");
    }

    @Override
    public String getBranch() {
        return new OutputProperties(commandOutput).property("branch");
    }

    @Override
    public String getFormattedDateTime() {
        return new OutputProperties(commandOutput).property("date");
    }

    @Override
    public Instant getDateTime() {
        return null;
    }

    @Override
    public Changeset getLeftParent() {
        return null;
    }

    @Override
    public Changeset getRightParent() {
        return null;
    }

    @Override
    public String getMessage() {
        return new OutputProperties(commandOutput).property("desc");
    }

    @Override
    public String getNode() {
        return new OutputProperties(commandOutput).property("node");
    }

    @Override
    public List<String> tags() {
        return Arrays.asList(new OutputProperties(commandOutput).property("tags").split(" ").clone());
    }

    @Override
    public String getRevision() {
        return new OutputProperties(commandOutput).property("rev");
    }

    @Override
    public String toString() {
        return "CommandLineChangeset{" +
                "user='" + getUser() + '\'' +
                ", branch='" + getBranch() + '\'' +
                ", formattedDateTime='" + getFormattedDateTime() + '\'' +
                ", dateTime=" + getDateTime() +
                ", leftParent=" + getLeftParent() +
                ", rightParent=" + getRightParent() +
                ", message='" + getMessage() + '\'' +
                ", node='" + getNode() + '\'' +
                ", tags=" + tags() +
                ", revision='" + getRevision() + '\'' +
                '}';
    }

    private static class Undefined extends CommandLineChangeset {
        public Undefined() {
            super("");
        }

        @Override
        public String getUser() {
            throw new UnsupportedOperationException("Method 'getUser' doesn't implemented");
        }

        @Override
        public String getBranch() {
            throw new UnsupportedOperationException("Method 'getBranch' doesn't implemented");
        }

        @Override
        public String getFormattedDateTime() {
            throw new UnsupportedOperationException("Method 'getFormattedDateTime' doesn't implemented");
        }

        @Override
        public Instant getDateTime() {
            throw new UnsupportedOperationException("Method 'getDateTime' doesn't implemented");
        }

        @Override
        public Changeset getLeftParent() {
            throw new UnsupportedOperationException("Method 'getLeftParent' doesn't implemented");
        }

        @Override
        public Changeset getRightParent() {
            throw new UnsupportedOperationException("Method 'getRightParent' doesn't implemented");
        }

        @Override
        public String getMessage() {
            throw new UnsupportedOperationException("Method 'getMessage' doesn't implemented");
        }

        @Override
        public String getNode() {
            throw new UnsupportedOperationException("Method 'getNode' doesn't implemented");
        }

        @Override
        public List<String> tags() {
            throw new UnsupportedOperationException("Method 'tags' doesn't implemented");
        }

        @Override
        public String getRevision() {
            throw new UnsupportedOperationException("Method 'getRevision' doesn't implemented");
        }
    }
}
