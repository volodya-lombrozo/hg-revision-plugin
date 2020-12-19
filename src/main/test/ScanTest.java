import domain.RepositoryInfo;
import util.exceptions.ExecuteException;
import domain.repo.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.Assert.*;

@Ignore("for manual testing only")
public class ScanTest {

    private static final String path = "D:\\workspace\\hg_repo";
    private static final Logger logger = Logger.getLogger(ScanTest.class.getName());

    @Test
    public void javaHgFillProperties() throws ExecuteException {
        Properties properties = new Properties();
        RepositoryInfo info = new RepositoryInfo(new JavaHgRepository(path));

        info.fillProperties(properties);

        logger.info(properties.toString());
        assertFalse(properties.isEmpty());
    }

    @Test
    public void commandLineRepoLoadParentsTest() throws ExecuteException {
        CommandLineRepository repository = new CommandLineRepository(path);
        Changeset changeset = repository.currentChangeset();

        Changeset rightParent = changeset.getRightParent();
        Changeset leftParent = changeset.getLeftParent();

        logger.info("Child: " + changeset);
        logger.info("Right parent: " + rightParent);
        logger.info("Left parent: " + leftParent);
        assertNotNull(changeset);
        assertNotNull(leftParent);
        assertNotNull(rightParent);
    }


    @Test
    public void commandLineFillPropertiesTest() throws ExecuteException {
        Properties properties = new Properties();
        RepositoryInfo info = new RepositoryInfo(new CachedRepository(new CommandLineRepository(path)));

        info.fillProperties(properties);

        logger.info(properties.toString());
        assertFalse(properties.isEmpty());
    }


    @Test
    public void compareTwoImplementations() throws ExecuteException {
        Repository javaHg = new JavaHgRepository(path);
        Repository commandLine = new CommandLineRepository(path);
        Properties javaHgProps = new Properties();
        Properties commandLineProps = new Properties();

        new RepositoryInfo(javaHg).fillProperties(javaHgProps);
        new RepositoryInfo(commandLine).fillProperties(commandLineProps);

        assertEquals(javaHgProps, commandLineProps);
    }


}
