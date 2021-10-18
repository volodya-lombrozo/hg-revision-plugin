package util.log;

public class MavenLog implements Log {

    private final org.apache.maven.plugin.logging.Log mavenLog;

    public MavenLog(final org.apache.maven.plugin.logging.Log mavenLog) {
        this.mavenLog = mavenLog;
    }

    @Override
    public void info(final String message) {
        mavenLog.info(message);
    }

    @Override
    public String name() {
        return "Maven Log";
    }

}
