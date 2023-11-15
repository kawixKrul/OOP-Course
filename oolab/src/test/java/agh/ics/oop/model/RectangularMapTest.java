package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings({"rawtypes", "unchecked"})
public class RectangularMapTest {
    @Test
    public void rectangularMapTest() {
        WorldMap worldMap = new RectangularMap(9,9);
        Animal north = new Animal(new Vector2d(4,9));
        Animal south = new Animal(new Vector2d(4,0));
        Animal east = new Animal(new Vector2d(9,4));
        Animal west = new Animal(new Vector2d(0,4));

        // check if animals can be placed on the map and are placed correctly
        assertTrue(worldMap.place(north));
        assertTrue(worldMap.place(south));
        assertTrue(worldMap.place(east));
        assertTrue(worldMap.place(west));
        assertTrue(worldMap.isOccupied(new Vector2d(4,9)));
        assertTrue(worldMap.isOccupied(new Vector2d(4,0)));
        assertTrue(worldMap.isOccupied(new Vector2d(9,4)));
        assertTrue(worldMap.isOccupied(new Vector2d(0,4)));
        assertEquals(north, worldMap.objectAt(new Vector2d(4,9)));
        assertEquals(south, worldMap.objectAt(new Vector2d(4,0)));
        assertEquals(east, worldMap.objectAt(new Vector2d(9,4)));
        assertEquals(west, worldMap.objectAt(new Vector2d(0,4)));

        // check if animals can be placed on the same position
        Animal north2 = new Animal(new Vector2d(4,9));
        Animal south2 = new Animal(new Vector2d(4,0));
        Animal east2 = new Animal(new Vector2d(9,4));
        Animal west2 = new Animal(new Vector2d(0,4));

        assertFalse(worldMap.place(north2));
        assertFalse(worldMap.place(south2));
        assertFalse(worldMap.place(east2));
        assertFalse(worldMap.place(west2));

        // check boundaries of the map
        assertFalse(worldMap.canMoveTo(new Vector2d(-1,0)));
        assertFalse(worldMap.canMoveTo(new Vector2d(0,-1)));
        assertFalse(worldMap.canMoveTo(new Vector2d(10,0)));
        assertFalse(worldMap.canMoveTo(new Vector2d(0,10)));

        // check if can move to occupied position
        assertFalse(worldMap.canMoveTo(new Vector2d(4,9)));
        assertFalse(worldMap.canMoveTo(new Vector2d(4,0)));
        assertFalse(worldMap.canMoveTo(new Vector2d(9,4)));
        assertFalse(worldMap.canMoveTo(new Vector2d(0,4)));

        // rotate animals
        south.move(MoveDirection.RIGHT, worldMap);
        south.move(MoveDirection.RIGHT, worldMap);
        east.move(MoveDirection.RIGHT, worldMap);
        west.move(MoveDirection.LEFT, worldMap);

        // move animals (they should stay in the same position)
        south.move(MoveDirection.FORWARD, worldMap);
        east.move(MoveDirection.FORWARD, worldMap);
        west.move(MoveDirection.FORWARD, worldMap);
        north.move(MoveDirection.FORWARD, worldMap);

        // check if animals are on correct positions
        assertEquals(new Vector2d(4,0), south.getPosition());
        assertEquals(new Vector2d(9,4), east.getPosition());
        assertEquals(new Vector2d(0,4), west.getPosition());
        assertEquals(new Vector2d(4,9), north.getPosition());

        // move one animal in more complicated way
        worldMap.move(south, MoveDirection.BACKWARD);
        worldMap.move(south, MoveDirection.BACKWARD);
        worldMap.move(south, MoveDirection.LEFT);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.LEFT);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);

        assertEquals(new Vector2d(7,4), south.getPosition());
        assertFalse(worldMap.isOccupied(new Vector2d(4,0)));
        assertFalse(worldMap.isOccupied(new Vector2d(4,1)));
    }
}
