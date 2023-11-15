package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    public void runTestAnimal() {
        // test constructor
        Animal a = new Animal();
        Animal b = new Animal(new Vector2d(4,4));
        RectangularMap worldMap = new RectangularMap(4,4);

        assertTrue(a.isAt(new Vector2d(2,2)));
        assertTrue(b.isAt(new Vector2d(4,4)));
        assertSame(a.getOrientation(), MapDirection.NORTH);
        assertSame(b.getOrientation(), MapDirection.NORTH);

        // Test if animal cannot move outside the map in North direction
        for(int i=0; i<6; i++) {
            a.move(MoveDirection.FORWARD, worldMap);
        }
        assertTrue(a.isAt(new Vector2d(2,4)));

        // Test if animal cannot move outside the map in South direction
        for(int i=0; i<6; i++) {
            a.move(MoveDirection.BACKWARD, worldMap);
        }
        assertTrue(a.isAt(new Vector2d(2,0)));

        // Test if animal cannot move outside the map in East direction and check orientation
        a.move(MoveDirection.RIGHT, worldMap);
        assertSame(a.getOrientation(), MapDirection.EAST);
        for(int i=0; i<6; i++) {
            a.move(MoveDirection.FORWARD, worldMap);
        }
        assertTrue(a.isAt(new Vector2d(4,0)));

        // Test if animal cannot move outside the map in West direction and check orientation
        a.move(MoveDirection.LEFT, worldMap);
        a.move(MoveDirection.LEFT, worldMap);
        assertSame(a.getOrientation(), MapDirection.WEST);
        for(int i=0; i<6; i++) {
            a.move(MoveDirection.FORWARD, worldMap);
        }
        assertTrue(a.isAt(new Vector2d(0,0)));

        // test if animal moves correctly
        b.move(MoveDirection.FORWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(4,4)));
        b.move(MoveDirection.LEFT, worldMap);
        assertTrue(b.isAt(new Vector2d(4,4)));
        b.move(MoveDirection.FORWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(3,4)));
        b.move(MoveDirection.FORWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(2,4)));
        b.move(MoveDirection.BACKWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(3,4)));
        b.move(MoveDirection.RIGHT, worldMap);
        assertTrue(b.isAt(new Vector2d(3,4)));
        b.move(MoveDirection.BACKWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(3,3)));
        b.move(MoveDirection.FORWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(3,4)));
        b.move(MoveDirection.FORWARD, worldMap);
        assertTrue(b.isAt(new Vector2d(3,4)));
    }
}
