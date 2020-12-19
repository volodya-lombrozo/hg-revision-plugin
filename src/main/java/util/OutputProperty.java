package util;

import java.util.Arrays;

public class OutputProperty {

    private final String commandOutput;
    private final String key;
    private final String delimiter;

    public OutputProperty(String commandOutput, String key) {
        this(commandOutput, key, "\n");
    }

    public OutputProperty(String commandOutput, String key, String delimiter) {
        this.commandOutput = commandOutput;
        this.key = key;
        this.delimiter = delimiter;
    }

    public String property() {
        String[] strings = commandOutput.split(delimiter);
        return Arrays.stream(strings).filter(s -> s.contains(key)).findFirst().map(this::value).orElse("");
    }

    private String value(String raw) {
        int start = raw.indexOf("'");
        int end = raw.lastIndexOf("'");
        if (start >= end) return "";
        return raw.substring(start + 1, end);
    }


}
