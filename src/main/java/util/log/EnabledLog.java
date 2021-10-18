package util.log;

public class EnabledLog implements Log {

    private final Log delegate;
    private final boolean enable;

    public EnabledLog(final Log delegate) {
        this(delegate, true);
    }

    public EnabledLog(final Log delegate, final boolean enabled) {
        this.delegate = delegate;
        this.enable = enabled;
    }

    @Override
    public void info(final String message) {
        if (enable) {
            delegate.info(message);
        }
    }

    @Override
    public String name() {
        return delegate.name();
    }
}
