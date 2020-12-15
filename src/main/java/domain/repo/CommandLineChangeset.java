package domain.repo;

import domain.command.OutputProperty;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandLineChangeset implements Changeset {

    private final String commandOutput;

    public CommandLineChangeset(String commandOutput) {
        this.commandOutput = commandOutput;
    }

    @Override
    public String getUser() {
        return new OutputProperty(commandOutput, "user").property();
    }

    @Override
    public String getBranch() {
        return new OutputProperty(commandOutput, "branch").property();
    }

    @Override
    public String getFormattedDateTime() {
        return new OutputProperty(commandOutput, "date").property();
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
        return new OutputProperty(commandOutput, "desc").property();
    }

    @Override
    public String getNode() {
        return new OutputProperty(commandOutput, "node").property();
    }

    @Override
    public List<String> tags() {
        return Arrays.asList(new OutputProperty(commandOutput, "tags").property().split(" ").clone());
    }

    @Override
    public String getRevision() {
        return new OutputProperty(commandOutput, "rev").property();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandLineChangeset that = (CommandLineChangeset) o;
        return Objects.equals(commandOutput, that.commandOutput);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandOutput);
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

}
