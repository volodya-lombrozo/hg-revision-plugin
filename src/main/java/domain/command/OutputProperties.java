package domain.command;

import java.util.Arrays;

public class OutputProperties {

    private final String commandOutput;
    private final String delimiter;

    public OutputProperties(String commandOutput) {
        this(commandOutput, "\n");
    }

    public OutputProperties(String commandOutput, String delimiter) {
        this.commandOutput = commandOutput;
        this.delimiter = delimiter;
    }

    public String property(String key) {
        String[] strings = commandOutput.split(delimiter);
        return Arrays.stream(strings).filter(s -> s.contains(key)).findFirst().map(this::value).orElse("");
    }

    private String value(String raw) {
        int start = raw.indexOf("'");
        int end = raw.lastIndexOf("'");
        return raw.substring(start + 1, end);
    }


}
