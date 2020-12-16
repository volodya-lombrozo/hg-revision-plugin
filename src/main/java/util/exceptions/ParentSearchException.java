package util.exceptions;

public class ParentSearchException extends RuntimeException {

    public ParentSearchException() {
        super("Can't find parent");
    }

    public ParentSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParentSearchException(Throwable cause) {
        super(cause);
    }
}
