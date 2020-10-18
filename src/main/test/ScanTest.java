import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import domain.RepositoryInfo;
import org.junit.Ignore;
import org.junit.Test;
import service.changeset.CurrentChangeSet;
import service.exceptions.ChangesetNotFound;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

@Ignore("For manual testing only")
public class ScanTest {

    @Test
    public void currentChangeSetTest() throws ChangesetNotFound {
        String pathname = "D:\\workspace\\hg_repo"; //put your path here
        Repository repository = Repository.open(new File(pathname));

        Properties properties = new Properties();
        RepositoryInfo info = new RepositoryInfo(repository);
        info.fillProperties(properties);

        System.out.println(properties);
        assertNotNull(repository);
        assertNotNull(repository.workingCopy().getParent1());
    }
}
