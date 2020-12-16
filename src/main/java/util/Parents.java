package util;

public class Parents {

    private final String raw;
    private final String EMPTY = "";

    public Parents(String raw) {
        this.raw = raw;
    }

    public String left() {
        if (isEmptyParents()) return EMPTY;
        String[] parsed = raw.trim().split(" ");
        return parsed[0];
    }

    public String right() {
        if (isEmptyParents()) return EMPTY;
        String[] parsed = raw.trim().split(" ");
        if (parsed.length > 1) {
            String right = parsed[1];
            if (right.startsWith("-"))
                return EMPTY;
            else return right;
        } else return EMPTY;
    }

    private boolean isEmptyParents() {
        return raw == null || raw.isEmpty();
    }
}
