package util.log;

import java.util.ArrayDeque;
import java.util.Deque;

public class TestLog implements Log {

    private final Deque<String> messages;

    public TestLog() {
        this(new ArrayDeque<>());
    }

    public TestLog(final Deque<String> messages) {
        this.messages = messages;
    }

    @Override
    public void info(final String message) {
        messages.add(message);
    }

    @Override
    public String name() {
        return "Test Log";
    }

    public String lastMessage() {
        return messages.getLast();
    }
}
