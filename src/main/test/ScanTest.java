import com.aragost.javahg.Repository;
import domain.RepositoryInfo;
import domain.command.ExecuteException;
import domain.repo.Changeset;
import domain.repo.CommandLineRepository;
import domain.repo.JavaHgRepository;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@Ignore("For manual testing only")
public class ScanTest {

    @Test
    @Ignore("for manual testing only")
    public void currentChangeSetTest() throws ExecuteException {
        String pathname = "D:\\workspace\\hg_repo"; //put your path here
        Repository repository = Repository.open(new File(pathname));

        Properties properties = new Properties();
        RepositoryInfo info = new RepositoryInfo(new JavaHgRepository(repository));
        info.fillProperties(properties);

        System.out.println(properties);
        assertNotNull(repository);
        assertNotNull(repository.workingCopy().getParent1());
    }

    @Test
    public void integrationTest() throws ExecuteException {
        CommandLineRepository repository = new CommandLineRepository("D:\\workspace\\hg_repo"); //pass your path there

        Changeset changeset = repository.currentChangeset();
        Changeset leftParent = changeset.getLeftParent();
        Changeset rightParent = changeset.getRightParent();

        System.out.println("Child: " + changeset);
        System.out.println("Left parent: " + leftParent);
        System.out.println("Right parent: " + rightParent);
        assertNotNull(changeset);
    }


    @Test
    public void commandLineScanTest() throws ExecuteException {
        String pathname = "D:\\workspace\\hg_repo"; //put your path here

        Properties properties = new Properties();
        RepositoryInfo info = new RepositoryInfo(new CommandLineRepository(pathname));
        info.fillProperties(properties);

        System.out.println(properties);
    }

    @Test
    public void compareTwoImplementations() {
        fail();//todo
    }


}
