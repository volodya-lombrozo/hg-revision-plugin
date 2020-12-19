package domain.repo;

import util.exceptions.ExecuteException;
import util.OutputProperty;
import util.time.FormattedDateTime;
import util.HgChangesetString;
import util.time.HgDateTime;
import util.Parents;
import util.exceptions.ParentSearchException;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CommandLineChangeset implements Changeset {

    private final String commandOutput;
    private final Repository repository;

    public CommandLineChangeset(String commandOutput) {
        this(commandOutput, new FakeRepository());
    }

    public CommandLineChangeset(Changeset changeset, Repository repository) {
        this(new HgChangesetString(changeset).toString(), repository);
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
        return new FormattedDateTime(getZonedDateTime()).toString();
    }

    @Override
    public ZonedDateTime getZonedDateTime() {
        String rawDate = new OutputProperty(commandOutput, "date").property();
        return new HgDateTime(rawDate).toZonedDateTime();
    }

    @Override
    public Changeset getLeftParent() {
        String rev = new Parents(parents()).left();
        try {
            return repository.findChangeset(rev);
        } catch (ExecuteException ex) {
            throw new ParentSearchException("Exception occurred during searching of left parent: " + rev + ". Changeset " + this, ex);
        }
    }

    @Override
    public Changeset getRightParent() {
        String rev = new Parents(parents()).right();
        try {
            return repository.findChangeset(rev);
        } catch (ExecuteException ex) {
            throw new ParentSearchException("Exception occurred during searching of right parent: " + rev + ". Changeset " + this, ex);
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
        String tags = new OutputProperty(commandOutput, "tags").property();
        if (tags == null || tags.isEmpty()) return Collections.emptyList();
        return Arrays.asList(tags.split(" ").clone());
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
                ", dateTime='" + getZonedDateTime() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", node='" + getNode() + '\'' +
                ", tags=" + tags() +
                ", revision='" + getRevision() + '\'' +
                ", parents='" + parents() + "'" +
                '}';
    }

}
