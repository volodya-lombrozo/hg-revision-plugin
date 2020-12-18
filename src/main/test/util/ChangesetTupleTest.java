package util;

import domain.repo.Changeset;
import domain.repo.CommandLineChangeset;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ChangesetTupleTest {


    @Test
    public void toMapTest() {
        String firstChangeset = "node:'1:revision'";
        String secondChangeset = "node:'2:revision'";
        String input = firstChangeset + "\n\n" + secondChangeset;
        CommandLineChangeset firstExpected = new CommandLineChangeset(firstChangeset);
        CommandLineChangeset secondExpected = new CommandLineChangeset(secondChangeset);

        Map<String, Changeset> actual = new ChangesetTuple(input).toMap();

        Changeset first = actual.get("1:revision");
        Changeset second = actual.get("2:revision");
        assertEquals(2, actual.size());
        assertEquals(firstExpected, first);
        assertEquals(secondExpected, second);

    }


}