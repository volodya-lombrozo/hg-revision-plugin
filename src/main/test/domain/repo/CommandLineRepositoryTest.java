package domain.repo;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineRepositoryTest {

    @Test
    public void currentChangeset() {
    }


    @Test
    @Ignore("for manual testing only")
    public void integrationTest(){
        CommandLineRepository repository = new CommandLineRepository("D:\\workspace\\hg_repo");

        Changeset changeset = repository.currentChangeset();

        System.out.println(changeset);
        assertNotNull(changeset);
    }
}