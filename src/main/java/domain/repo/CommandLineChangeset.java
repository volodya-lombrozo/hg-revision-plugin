package domain.repo;

import domain.command.ExecuteException;
import domain.command.OutputProperty;
import util.FormattedDateTime;
import util.HgDateTime;
import util.exceptions.ParentSearchException;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommandLineChangeset implements Changeset {

    private final String commandOutput;
    private final Repository repository;

    public CommandLineChangeset(String commandOutput) {
        this(commandOutput, new FakeRepository());
    }

    public CommandLineChangeset(String commandOutput, Repository repository) {
        this.commandOutput = commandOutput;
        this.repository = repository;
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
        return new FormattedDateTime(getDateTime()).toString();
    }

    @Override
    public Instant getDateTime() {
        String rawDate = new OutputProperty(commandOutput, "date").property();
        return new HgDateTime(rawDate).toInstant();
    }

    //fixme: ugly realization
    @Override
    public Changeset getLeftParent() {
        try {
            String[] split = parents().trim().split(" ");
            if (split.length > 0)
                return repository.findChangeset(split[0]);
            else return null;
        } catch (ExecuteException ex) {
            throw new ParentSearchException("Exception occurred during searching of left parent. Changeset " + this, ex);
        }
    }

    //fixme: ugly realization
    @Override
    public Changeset getRightParent() {
        try {
            String[] split = parents().trim().split(" ");
            if (split.length > 1)
                return repository.findChangeset(split[1]);
            else return null;
        } catch (ExecuteException ex) {
            throw new ParentSearchException("Exception occurred during searching of right parent. Changeset " + this, ex);
        }
    }

    @Override
    public String getMessage() {
        return new OutputProperty(commandOutput, "message").property();
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

    private String parents() {
        return new OutputProperty(commandOutput, "parents").property().trim();
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
                ", dateTime='" + getDateTime() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", node='" + getNode() + '\'' +
                ", tags=" + tags() +
                ", revision='" + getRevision() + '\'' +
                ", parents='" + parents() + "'" +
                '}';
    }

}
