import com.aragost.javahg.Repository;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

@Ignore("Form manual testing only")
public class SomeTest {

    @Test
    public void currentChangeSetTest() {
        String pathname = "D:\\workspace\\hg-revision-plugin-example"; //put your path here
        Repository repository = Repository.open(new File(pathname));
        assertNotNull(repository);
        assertNotNull(repository.workingCopy().getParent1());
    }
}
