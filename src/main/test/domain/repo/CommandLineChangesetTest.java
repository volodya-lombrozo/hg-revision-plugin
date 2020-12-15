package domain.repo;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandLineChangesetTest {

    @Test
    @Ignore("Not worked")
    public void toStringTest() {
        String author = "author";
        String branch = "branch";
        String date = "12.12.2012";
        String message = "message";
        String node = "node";
        String tags = "a b";
        String revision = "rev";
        CommandLineChangeset changeset = new CommandLineChangeset(
                "user:" + "'" + author + "'\n" +
                        "branch:" + "'" + branch + "'\n" +
                        "date:" + "'" + date + "'\n" +
                        "desc:" + "'" + message + "'\n" +
                        "node:" + "'" + node + "'\n" +
                        "tags:" + "'" + tags + "'\n" +
                        "revision:" + "'" + revision + "'\n"
        );

        String actual = changeset.toString();

        String expectedFormattedDate = "";
        String expectedTags = tags.replace(" ", ", ");
        String expected = "CommandLineChangeset{user='" + author + "', branch='" + branch + "', " +
                "formattedDateTime='" + expectedFormattedDate + "', dateTime='" + date + "', message='" + message + "'," +
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