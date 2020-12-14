package domain.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class InputString {
    private final InputStream is;
    private final String delimiter;

    public InputString(InputStream is) {
        this(is, "\n");
    }

    public InputString(InputStream is, String delimiter) {
        this.is = is;
        this.delimiter = delimiter;
    }

    String read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String res = br.lines().collect(Collectors.joining(delimiter));
        is.close();
        return res;
    }

}
