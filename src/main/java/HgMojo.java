import domain.RepositoryInfo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import service.RepositorySteward;


@Mojo(name = "scan")
public class HgMojo extends AbstractMojo {

    @Parameter(defaultValue = "${basedir}", readonly = true)
    private String baseDir;

    @Parameter(defaultValue = "${project}")
    private MavenProject project;

    private RepositorySteward scanner = new RepositorySteward(baseDir);

    public void execute() throws MojoExecutionException {
        try {
            getLog().info("Hg project dir: " + baseDir);
            getLog().info("Start scanning of hg project");
            RepositoryInfo info = new RepositoryInfo(scanner.openRepository());
            getLog().info("Starting setting properties...");
            info.fillProperties(project.getProperties());
            getLog().info("Scanning is done!");
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }


}
