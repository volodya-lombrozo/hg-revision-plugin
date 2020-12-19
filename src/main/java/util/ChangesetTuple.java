package util;

import domain.repo.Changeset;
import domain.repo.CommandLineChangeset;
import domain.repo.Repository;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChangesetTuple {

    private final String rawListOfChangesets;

    public ChangesetTuple(String rawListOfChangesets) {
        this.rawListOfChangesets = rawListOfChangesets;
    }

    public Map<String, Changeset> toMap(Repository repo) {
        Pattern p = Pattern.compile("\\n[\\n]+");
        return Arrays.stream(rawListOfChangesets.split(p.pattern()))
                .map(output -> new CommandLineChangeset(output, repo)).collect(Collectors.toMap(this::key, c -> c));
    }

    private String key(Changeset changeset) {
        return changeset.getRevision() + ":" + changeset.getNode();
    }
}
