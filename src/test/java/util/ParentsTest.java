package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParentsTest {

    @Test
    public void left() {
        String twoParents = "4:370d3f31c2dd 10:2d7430ab8364";
        String expected = "4:370d3f31c2dd";

        String actual = new StringParents(twoParents).left();

        assertEquals(expected, actual);
    }

    @Test
    public void right() {
        String twoParents = "4:370d3f31c2dd 10:2d7430ab8364";
        String expected = "10:2d7430ab8364";

        String actual = new StringParents(twoParents).right();

        assertEquals(expected, actual);
    }


    @Test
    public void rightNegative() {
        String twoParents = "3:5d5f431c95a7f6e3d839d83a6cd4d7fa8504f718 -1:0000000000000000000000000000000000000000";

        String actual = new StringParents(twoParents).right();

        assertTrue(actual.isEmpty());
    }


    @Test
    public void left_onlyOneParent() {
        String onlyOne = "4:370d3f31c2dd";
        String expected = "4:370d3f31c2dd";

        String actual = new StringParents(onlyOne).left();

        assertEquals(expected, actual);
    }

    @Test
    public void right_onlyOneParent() {
        String onlyOne = "4:370d3f31c2dd";

        String actual = new StringParents(onlyOne).right();

        assertEquals("", actual);
    }

    @Test
    public void emptyParents() {
        StringParents parents = new StringParents("");

        String left = parents.left();
        String right = parents.right();

        assertTrue(left.isEmpty());
        assertTrue(right.isEmpty());
    }
}