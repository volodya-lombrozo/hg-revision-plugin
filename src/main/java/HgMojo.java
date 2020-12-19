import domain.RepositoryInfo;
import domain.repo.JavaHgRepository;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.PrintWriter;
import java.io.StringWriter;


@Mojo(name = "scan")
public class HgMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}", readonly = true)
    private String baseDir;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;


    public void execute() throws MojoExecutionException {
        try {
            getLog().info("Hg project dir: " + baseDir);
            getLog().info("Start of scanning hg project");
            RepositoryInfo info = new RepositoryInfo(new JavaHgRepository(baseDir));
            getLog().info("Start of setting properties...");
            info.fillProperties(project.getProperties());
            getLog().info("Scanning is done successfully.");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new MojoExecutionException(e.getMessage() + " " + sw.toString(), e);
        }
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }


}
