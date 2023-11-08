package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void equals() {
        assertNotEquals(new Vector2d(1, 1), new Vector2d(3, 1));
        assertEquals(new Vector2d(4, 1), new Vector2d(4, 1));
        assertEquals(new Vector2d(1,-4), new Vector2d(1,-4));
    }

    @Test
    public void testToString() {
        assertEquals("(1,1)", new Vector2d(1,1).toString());
        assertEquals("(0,-3)", new Vector2d(0,-3).toString());
    }

    @Test
    public void precedes() {
        assertTrue(new Vector2d(1, 1).precedes(new Vector2d(2, 3)));
        assertTrue(new Vector2d(1,1).precedes(new Vector2d(1,1)));
        assertFalse(new Vector2d(1,1).precedes(new Vector2d(-3,4)));
    }

    @Test
    public void follows() {
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(-2, -3)));
        assertTrue(new Vector2d(1,1).follows(new Vector2d(1,1)));
        assertFalse(new Vector2d(1,1).follows(new Vector2d(3,4)));
    }

    @Test
    public void upperRight() {
        assertEquals(new Vector2d(1,1), new Vector2d(1,0).upperRight(new Vector2d(0,1)));
        assertNotEquals(new Vector2d(1,1), new Vector2d(1,0).upperRight(new Vector2d(1,0)));
    }

    @Test
    public void lowerLeft() {
        assertEquals(new Vector2d(1,1), new Vector2d(1,2).lowerLeft(new Vector2d(2,1)));
        assertNotEquals(new Vector2d(1,1), new Vector2d(1,0).lowerLeft(new Vector2d(1,0)));
    }

    @Test
    public void add() {
        assertEquals(new Vector2d(1,1), new Vector2d(1,0).add(new Vector2d(0,1)));
    }

    @Test
    public void subtract() {
        assertEquals(new Vector2d(1,1), new Vector2d(2,2).subtract(new Vector2d(1,1)));
    }

    @Test
    public void opposite() {
        assertEquals(new Vector2d(0,0), new Vector2d(0,0).opposite());
        assertEquals(new Vector2d(1,-1), new Vector2d(-1,1).opposite());
    }
}
