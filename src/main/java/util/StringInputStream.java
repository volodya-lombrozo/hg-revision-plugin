package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class StringInputStream {
    private final InputStream is;
    private final String delimiter;

    public StringInputStream(InputStream is) {
        this(is, "\n");
    }

    public StringInputStream(InputStream is, String delimiter) {
        this.is = is;
        this.delimiter = delimiter;
    }

    public String read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String res = br.lines().collect(Collectors.joining(delimiter));
        is.close();
        return res;
    }

}
