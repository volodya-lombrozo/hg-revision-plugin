import domain.RepositoryInfo;
import domain.RepositoryInfoFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import util.log.EnabledLog;
import util.log.Log;
import util.log.MavenLog;

import java.io.PrintWriter;
import java.io.StringWriter;


@Mojo(name = "scan")
public class HgMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}", readonly = true)
    private String baseDir;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    @Parameter(property = "log", defaultValue = "false")
    private boolean isLogged = false;

    Log log = new EnabledLog(new MavenLog(getLog()), isLogged);

    private RepositoryInfoFactory factory = path -> new RepositoryInfo(path, log);

    public void execute() throws MojoExecutionException {
        try {
            log.info("Hg project dir: " + baseDir);
            log.info("Start of scanning hg project");
            RepositoryInfo info = factory.repositoryInfo(baseDir);
            log.info("Start of setting properties...");
            info.fillProperties(project.getProperties());
            log.info("Scanning is done successfully.");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new MojoExecutionException(e.getMessage() + " " + sw, e);
        }
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public void setFactory(RepositoryInfoFactory factory) {
        this.factory = factory;
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(final boolean logged) {
        this.isLogged = logged;
    }
}
