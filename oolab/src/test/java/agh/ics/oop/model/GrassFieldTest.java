package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GrassFieldTest {
    @Test
    public void testGrassField() {
        // basically the same as RectangularMapTest
        // as both classes inherit most of the methods from AbstractWorldMap

        WorldMap worldMap = new GrassField(10);
        Set<Grass> grasses = worldMap.getElements()
                .stream()
                .filter(x -> x instanceof Grass)
                .map(x -> (Grass) x)
                .collect(Collectors.toSet());

        // test for generating proper distinct grasses
        assertEquals(10, grasses.size());

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
        // boundaries for this map are nonexistent
        assertTrue(worldMap.canMoveTo(new Vector2d(-1,0)));
        assertTrue(worldMap.canMoveTo(new Vector2d(0,-1)));
        assertTrue(worldMap.canMoveTo(new Vector2d(10,0)));
        assertTrue(worldMap.canMoveTo(new Vector2d(0,10)));


        // move one animal in more complicated way the grass should not block its movement
        worldMap.move(south, MoveDirection.BACKWARD);
        worldMap.move(south, MoveDirection.BACKWARD);
        worldMap.move(south, MoveDirection.LEFT);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.LEFT);
        worldMap.move(south, MoveDirection.FORWARD);
        worldMap.move(south, MoveDirection.FORWARD);

        assertEquals(new Vector2d(1,-4), south.getPosition());

        // there is no reliable way to test the grass distribution
        // and what cells of the map are occupied because
        // the grass is generated randomly
    }
}
