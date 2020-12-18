package util;

import domain.repo.Changeset;
import domain.repo.CommandLineChangeset;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChangesetTuple {

    private final String rawListOfChangesets;

    public ChangesetTuple(String rawListOfChangesets) {
        this.rawListOfChangesets = rawListOfChangesets;
    }

    public Map<String, Changeset> toMap() {
        Pattern p = Pattern.compile("\\n[\\n]+");
        return Arrays.stream(rawListOfChangesets.split(p.pattern()))
                .map(CommandLineChangeset::new).collect(Collectors.toMap(CommandLineChangeset::getNode, c -> c));
    }

}
