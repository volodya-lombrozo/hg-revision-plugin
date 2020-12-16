package domain.repo;

import org.junit.Test;
import util.FormattedDateTime;
import util.HgDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CommandLineChangesetTest {

    @Test
    public void toStringTest() {
        String author = "author";
        String branch = "branch";
        String date = "2020-10-19 11:53 +0300";
        String message = "message";
        String node = "node";
        String tags = "a b";
        String revision = "rev";
        CommandLineChangeset changeset = new CommandLineChangeset(
                "user:" + "'" + author + "'\n" +
                        "branch:" + "'" + branch + "'\n" +
                        "date:" + "'" + date + "'\n" +
                        "message:" + "'" + message + "'\n" +
                        "node:" + "'" + node + "'\n" +
                        "tags:" + "'" + tags + "'\n" +
                        "revision:" + "'" + revision + "'\n"
        );

        String actual = changeset.toString();

        String expectedDate = new HgDateTime(date).toInstant().toString();
        String expectedFormattedDate = new FormattedDateTime(new HgDateTime(date).toInstant()).toString();
        String expectedTags = tags.replace(" ", ", ");
        String expected = "CommandLineChangeset{user='" + author + "', branch='" + branch + "', " +
                "formattedDateTime='" + expectedFormattedDate + "', dateTime='" + expectedDate + "', message='" + message + "'," +
                " node='" + node + "', tags=[" + expectedTags + "], revision='" + revision + "'}";
        assertEquals(expected, actual);
    }

    @Test
    public void hashCodeTest() {
        CommandLineChangeset changeset = new CommandLineChangeset("some output");

        int hashCode = changeset.hashCode();

        assertNotEquals(0, hashCode);
    }


}