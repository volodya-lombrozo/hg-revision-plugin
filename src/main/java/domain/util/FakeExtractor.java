package domain.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FakeExtractor implements BookmarksExtractor {

    private final List<String> bookmarks;

    public FakeExtractor(String... bookmarks) {
        this(Arrays.stream(bookmarks).collect(Collectors.toList()));
    }

    public FakeExtractor(List<String> bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public List<String> extract() {
        return bookmarks;
    }
}
