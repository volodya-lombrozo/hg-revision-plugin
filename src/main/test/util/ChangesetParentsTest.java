package util;

import domain.repo.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ChangesetParentsTest {

    private final String leftNode = "asdasdasd";
    private final String rightNode = "zxczxcxczx";
    private final String leftRev = "10";
    private final String rightRev = "9";
    private final String left = leftRev + ":" + leftNode;
    private final String right = rightRev + ":" + rightNode;
    private final String full = left + " " + right;


    Changeset changeset;
    Changeset leftChangeset;
    Changeset rightChangeset;

    @Before
    public void setUp() {
        changeset = Mockito.mock(Changeset.class);
        leftChangeset = Mockito.mock(Changeset.class);
        rightChangeset = Mockito.mock(Changeset.class);
        when(leftChangeset.getNode()).thenReturn(leftNode);
        when(leftChangeset.getRevision()).thenReturn(leftRev);
        when(rightChangeset.getNode()).thenReturn(rightNode);
        when(rightChangeset.getRevision()).thenReturn(rightRev);
    }

    @Test
    public void toStringTest_allExists() {
        when(changeset.getLeftParent()).thenReturn(leftChangeset);
        when(changeset.getRightParent()).thenReturn(rightChangeset);
        ChangesetParents parents = new ChangesetParents(changeset);

        String actual = parents.toString();

        assertEquals(full, actual);
    }

    @Test
    public void toStringTest_onlyLeft() {
        when(changeset.getLeftParent()).thenReturn(leftChangeset);
        ChangesetParents parents = new ChangesetParents(changeset);

        String actual = parents.toString();

        assertEquals(left, actual);
    }

    @Test
    public void toStringTest_onlyRight() {
        when(changeset.getRightParent()).thenReturn(rightChangeset);
        ChangesetParents parents = new ChangesetParents(changeset);

        String actual = parents.toString();

        assertEquals(right, actual);
    }


    @Test
    public void toStringTest_emptyPatents() {
        ChangesetParents parents = new ChangesetParents(changeset);

        String actual = parents.toString();

        assertTrue(actual.isEmpty());
    }
}