package domain;

import com.aragost.javahg.Changeset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CommitNumberTest {

    private final Changeset changeset = Mockito.mock(Changeset.class);

    @Before
    public void setUp() {
        when(changeset.getBranch()).thenReturn("master");
    }

    @Test
    public void fillProperties() {
        oneChild();
        RecordableProperty commitNumber = new CommitNumber(changeset);
        Properties properties = new Properties();

        commitNumber.fillProperties(properties);

        String key = "hg.commit.number";
        assertTrue(properties.containsKey(key));
        assertEquals("2", properties.get(key));
    }

    @Test
    public void fillProperties_fiveChildrenAsDifficultLoop() {
        fiveChildrenAsLoop();
        RecordableProperty commitNumber = new CommitNumber(changeset);
        Properties properties = new Properties();

        commitNumber.fillProperties(properties);

        String key = "hg.commit.number";
        assertTrue(properties.containsKey(key));
        assertEquals("6", properties.get(key));
    }


    private void oneChild() {
        String expectedBranch = "branch";
        when(changeset.getBranch()).thenReturn(expectedBranch);
        Changeset child = Mockito.mock(Changeset.class);
        when(child.getBranch()).thenReturn(expectedBranch);
        when(changeset.getParent2()).thenReturn(child);
    }


    private void fiveChildrenAsLoop() {
        Changeset child = rightChild(leftChild(leftChild(this.changeset)));
        when(changeset.getParent2()).thenReturn(child);
        leftChild(child);
        rightChild(child);
    }

    private Changeset leftChild(Changeset changeset) {
        return leftChild(changeset, changeset.getBranch());
    }

    private Changeset leftChild(Changeset changeset, String branch) {
        Changeset child = Mockito.mock(Changeset.class);
        when(child.getBranch()).thenReturn(branch);
        when(changeset.getParent1()).thenReturn(child);
        return child;
    }

    private Changeset rightChild(Changeset changeset) {
        return rightChild(changeset, changeset.getBranch());
    }

    private Changeset rightChild(Changeset changeset, String branch) {
        Changeset child = Mockito.mock(Changeset.class);
        when(child.getBranch()).thenReturn(branch);
        when(changeset.getParent2()).thenReturn(child);
        return child;
    }
}