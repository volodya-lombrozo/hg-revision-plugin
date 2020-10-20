import com.aragost.javahg.Repository;
import domain.RepositoryInfo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import service.RepositorySteward;

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
            RepositorySteward scanner = new RepositorySteward(baseDir);
            getLog().info("Hg project dir: " + baseDir);
            getLog().info("Start scanning of hg project");
            Repository repository = scanner.openRepository();
            getLog().info("Repository was opened");
            RepositoryInfo info = new RepositoryInfo(repository);
            getLog().info("Starting setting properties...");
            info.fillProperties(project.getProperties());
            getLog().info("Scanning is done!");
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
