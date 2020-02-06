import domain.RepoInfo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import service.PropertiesSetter;
import service.RepositoryScanner;

@Mojo(name = "scan")
public class HgMojo extends AbstractMojo {


    @Parameter(defaultValue = "${basedir}", readonly = true)
    private String baseDir;

    private RepositoryScanner scanner = new RepositoryScanner();
    private PropertiesSetter propertiesSetter = new PropertiesSetter();

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            getLog().info("Hg project dir: " + baseDir);
            getLog().info("Start scanning of hg project");
            RepoInfo repositoryInfo = scanner.scan(baseDir);
            getLog().info("Starting setting properties...");
            propertiesSetter.setProperties(repositoryInfo);
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
