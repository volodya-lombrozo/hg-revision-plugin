package util;

import domain.repo.CommandLineChangeset;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class HgChangesetStringTest {

    @Test
    @Ignore("Not finished yet")
    public void conversionTest() {
        String author = "author";
        String branch = "branch";
        String date = "1567677484 -10800";
        String message = "message";
        String node = "node";
        String tags = "a b";
        String revision = "rev";
        String parents = "4:370d3f31c2dd 10:2d7430ab8364";
        String expected = "user:" + "'" + author + "'\n" +
                "branch:" + "'" + branch + "'\n" +
                "date:" + "'" + date + "'\n" +
                "message:" + "'" + message + "'\n" +
                "node:" + "'" + node + "'\n" +
                "tags:" + "'" + tags + "'\n" +
                "revision:" + "'" + revision + "'\n" +
                "parents:" + "'" + parents;
        CommandLineChangeset changeset = new CommandLineChangeset(expected);

        String actual = new HgChangesetString(changeset).toString();

        assertEquals(expected, actual);
    }


}