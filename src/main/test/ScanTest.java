import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import domain.RepoInfo;
import org.junit.Ignore;
import org.junit.Test;
import service.changeset.CurrentChangeSet;
import service.exceptions.ChangesetNotFound;

import java.io.File;

import static org.junit.Assert.assertNotNull;

//@Ignore("Form manual testing only")
public class ScanTest {

    @Test
    public void currentChangeSetTest() throws ChangesetNotFound {
        String pathname = "D:\\workspace\\hg_repo"; //put your path here
        Repository repository = Repository.open(new File(pathname));

        RepoInfo info = new CurrentChangeSet(repository).toRepoInfo();

        System.out.println(info);
        assertNotNull(repository);
        assertNotNull(repository.workingCopy().getParent1());
    }
}
