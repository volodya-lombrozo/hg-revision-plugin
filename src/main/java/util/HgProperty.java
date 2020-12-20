package util;

import java.util.Arrays;

public class HgProperty {

    private final String commandOutput;
    private final String key;
    private final String delimiter;

    public HgProperty(String commandOutput, String key) {
        this(commandOutput, key, "\n");
    }

    public HgProperty(String commandOutput, String key, String delimiter) {
        this.commandOutput = commandOutput;
        this.key = key;
        this.delimiter = delimiter;
    }

    public String property() {
        String[] strings = commandOutput.split(delimiter);
        return Arrays.stream(strings).filter(s -> filterKey(s, key)).findFirst().map(this::value).orElse("");
    }

    private boolean filterKey(String raw, String key) {
        String keyFromRaw = raw.split(":")[0];
        return key.equals(keyFromRaw);
    }

    private String value(String raw) {
        int start = raw.indexOf("'");
        int end = raw.lastIndexOf("'");
        if (start >= end) return "";
        return raw.substring(start + 1, end);
    }


}
