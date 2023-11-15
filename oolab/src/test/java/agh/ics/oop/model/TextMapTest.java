package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TextMapTest {
    @Test
    public void textMapTest() {
        // create map and check adding elements
        TextMap<Integer, String> worldMap = new TextMap<>(new ArrayList<>());

        worldMap.place("Ala");
        assertEquals(worldMap.objectAt(0), "Ala");

        worldMap.place("ma");
        assertEquals(worldMap.objectAt(1), "ma");

        worldMap.place("sowoniedźwiedzia");
        assertEquals(worldMap.objectAt(2), "sowoniedźwiedzia");

        assertNull(worldMap.objectAt(3));

        // check if elements are occupied
        assertTrue(worldMap.isOccupied(0));
        assertTrue(worldMap.isOccupied(1));
        assertTrue(worldMap.isOccupied(2));
        assertFalse(worldMap.isOccupied(3));

        assertFalse(worldMap.canMoveTo(new Vector2d(-1, 0)));
        assertTrue(worldMap.canMoveTo(new Vector2d(1, 0)));
        assertFalse(worldMap.canMoveTo(new Vector2d(4, 0)));

        // check moving elements
        worldMap.move("Ala", MoveDirection.RIGHT);
        assertEquals(worldMap.objectAt(0), "ma");
        assertEquals(worldMap.objectAt(1), "Ala");

        worldMap.move("sowoniedźwiedzia", MoveDirection.LEFT);
        assertEquals(worldMap.objectAt(1), "sowoniedźwiedzia");
        assertEquals(worldMap.objectAt(2), "Ala");

        worldMap.move("ma", MoveDirection.FORWARD);
        assertEquals(worldMap.objectAt(0), "ma");

        worldMap.move("sowoniedźwiedzia", MoveDirection.BACKWARD);
        assertEquals(worldMap.objectAt(1), "sowoniedźwiedzia");

        worldMap.move("Ala", MoveDirection.RIGHT);
        assertEquals(worldMap.objectAt(0), "ma");
        assertEquals(worldMap.objectAt(1), "sowoniedźwiedzia");
        assertEquals(worldMap.objectAt(2), "Ala");

        worldMap.move("ma", MoveDirection.LEFT);
        assertEquals(worldMap.objectAt(0), "ma");
        assertEquals(worldMap.objectAt(1), "sowoniedźwiedzia");
        assertEquals(worldMap.objectAt(2), "Ala");
    }
}
