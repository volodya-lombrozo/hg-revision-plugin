package util;

import domain.repo.Changeset;
import domain.repo.CommandLineChangeset;
import domain.repo.Repository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ChangesetTupleTest {


    @Test
    public void toMapTest() {
        String firstChangeset = "rev:'1'\nnode:'firstNode'";
        String secondChangeset = "rev:'2'\nnode:'secondNode'";
        String input = firstChangeset + "\n\n" + secondChangeset;
        CommandLineChangeset firstExpected = new CommandLineChangeset(firstChangeset);
        CommandLineChangeset secondExpected = new CommandLineChangeset(secondChangeset);

        Map<String, Changeset> actual = new ChangesetTuple(input).toMap(Mockito.mock(Repository.class));

        Changeset first = actual.get("1:firstNode");
        Changeset second = actual.get("2:secondNode");
        assertEquals(2, actual.size());
        assertEquals(firstExpected, first);
        assertEquals(secondExpected, second);

    }


}