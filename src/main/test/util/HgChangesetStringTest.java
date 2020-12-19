package util;

import util.exceptions.ExecuteException;
import domain.repo.CommandLineChangeset;
import domain.repo.Repository;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HgChangesetStringTest {

    @Test
    public void conversionTest() throws ExecuteException {
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
                "parents:" + "'" + parents + "'";
        Repository repo = Mockito.mock(Repository.class);
        when(repo.findChangeset("4:370d3f31c2dd")).thenReturn(new CommandLineChangeset("rev:'4'\nnode:'370d3f31c2dd'"));
        when(repo.findChangeset("10:2d7430ab8364")).thenReturn(new CommandLineChangeset("rev:'10'\nnode:'2d7430ab8364'"));
        CommandLineChangeset changeset = new CommandLineChangeset(expected, repo);

        String actual = new HgChangesetString(changeset).toString();

        assertEquals(expected, actual);
    }


}