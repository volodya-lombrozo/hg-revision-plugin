import domain.RepositoryInfo;
import domain.RepositoryInfoFactory;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import util.exceptions.ExecuteException;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class HgMojoTest {

    HgMojo hgMojo;
    MavenProject project;
    RepositoryInfoFactory factory;
    RepositoryInfo repositoryInfo;

    @Before
    public void setUp() {
        hgMojo = new HgMojo();
        project = new MavenProject();
        factory = Mockito.mock(RepositoryInfoFactory.class);
        repositoryInfo = Mockito.mock(RepositoryInfo.class);
        when(factory.repositoryInfo(any())).thenReturn(repositoryInfo);
        hgMojo.setFactory(factory);
        hgMojo.setProject(project);
    }

    @Test
    public void executeTest() throws MojoExecutionException, ExecuteException {
        Properties properties = new Properties();

        hgMojo.execute();

        verify(repositoryInfo, times(1)).fillProperties(properties);
    }

    @Test(expected = MojoExecutionException.class)
    public void executeExceptionTest() throws ExecuteException, MojoExecutionException {
        doThrow(new ExecuteException("Some exception")).when(repositoryInfo).fillProperties(any());

        hgMojo.execute();

        fail();
    }

    @Test
    public void baseDirTesting() {
        String expected = "/path";

        hgMojo.setBaseDir(expected);

        assertEquals(expected, hgMojo.getBaseDir());
    }

    @Test
    public void logging() {
        hgMojo.setLogged(true);
        assertTrue(hgMojo.isLogged());
    }
}