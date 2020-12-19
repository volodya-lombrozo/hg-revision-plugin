package domain.repo;

import util.exceptions.ExecuteException;
import org.junit.Test;
import org.mockito.Mockito;
import util.time.FormattedTime;
import util.time.StringHgTime;
import util.exceptions.ParentSearchException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CommandLineChangesetTest {

    @Test
    public void toStringTest() {
        String author = "author";
        String branch = "branch";
        String date = "1567677484 -10800";
        String message = "message";
        String node = "node";
        String tags = "a b";
        String revision = "rev";
        String parents = "4:370d3f31c2dd 10:2d7430ab8364";
        CommandLineChangeset changeset = new CommandLineChangeset(
                "user:" + "'" + author + "'\n" +
                        "branch:" + "'" + branch + "'\n" +
                        "date:" + "'" + date + "'\n" +
                        "message:" + "'" + message + "'\n" +
                        "node:" + "'" + node + "'\n" +
                        "tags:" + "'" + tags + "'\n" +
                        "revision:" + "'" + revision + "'\n" +
                        "parents:" + "'" + parents + "'\n"
        );

        String actual = changeset.toString();

        String expectedDate = new StringHgTime(date).toZonedDateTime().toString();
        String expectedFormattedDate = new FormattedTime(new StringHgTime(date).toZonedDateTime()).toString();
        String expectedTags = tags.replace(" ", ", ");
        String expected = "CommandLineChangeset{user='" + author + "', branch='" + branch + "', " +
                "formattedDateTime='" + expectedFormattedDate + "', dateTime='" + expectedDate + "', message='" + message + "'," +
                " node='" + node + "', tags=[" + expectedTags + "], revision='" + revision + "', parents='" + parents + "'}";
        assertEquals(expected, actual);
    }

    @Test
    public void leftParentTest() throws ExecuteException {
        Repository repo = Mockito.mock(Repository.class);
        Changeset changeset = new CommandLineChangeset("parents:'4:370d3f31c2dd 10:2d7430ab8364'", repo);
        Changeset expected = Mockito.mock(Changeset.class);
        when(repo.findChangeset("4:370d3f31c2dd")).thenReturn(expected);

        Changeset actual = changeset.getLeftParent();

        assertEquals(expected, actual);
    }

    @Test
    public void rightParentTest() throws ExecuteException {
        Repository repo = Mockito.mock(Repository.class);
        Changeset changeset = new CommandLineChangeset("parents:'4:370d3f31c2dd 10:2d7430ab8364'", repo);
        Changeset expected = Mockito.mock(Changeset.class);
        when(repo.findChangeset("10:2d7430ab8364")).thenReturn(expected);

        Changeset actual = changeset.getRightParent();

        assertEquals(expected, actual);
    }


    @Test(expected = ParentSearchException.class)
    public void leftParent_failDuringExecuteCommand() throws ExecuteException {
        Repository repo = Mockito.mock(Repository.class);
        Changeset changeset = new CommandLineChangeset("parents:'4:370d3f31c2dd 10:2d7430ab8364'", repo);
        when(repo.findChangeset("4:370d3f31c2dd")).thenThrow(new ExecuteException("Some execution fail"));

        changeset.getLeftParent();

        fail();
    }


    @Test(expected = ParentSearchException.class)
    public void rightParent_failDuringExecuteCommand() throws ExecuteException {
        Repository repo = Mockito.mock(Repository.class);
        Changeset changeset = new CommandLineChangeset("parents:'4:370d3f31c2dd 10:2d7430ab8364'", repo);
        when(repo.findChangeset("10:2d7430ab8364")).thenThrow(new ExecuteException("Some execution fail"));

        changeset.getRightParent();

        fail();
    }

    @Test
    public void hashCodeTest() {
        CommandLineChangeset changeset = new CommandLineChangeset("some output");

        int hashCode = changeset.hashCode();

        assertNotEquals(0, hashCode);
    }


}