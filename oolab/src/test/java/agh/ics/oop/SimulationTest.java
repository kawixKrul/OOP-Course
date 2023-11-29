package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SimulationTest {
    @Test
    public void runTestSimulation() {
        List<Vector2d> positions = List.of(
                new Vector2d(2, 2),
                new Vector2d(3, 4),
                new Vector2d(1, 1),
                new Vector2d(0, 0)
        );

        AbstractWorldMap worldMap1 = new RectangularMap(4, 4);

        String[] directionsStrings = {"FORWARD", "BACKWARD", "RIGHT", "LEFT"};

        List<Integer> directionsInts = List.of(
                0,1,3,2,3,3,2,1,2,3,1,2,3,1,1,1,2,2,3,3,2,2,1,1,0,0,0,0,0,0,0,0,0,2,3,3,0
        );

        List<MoveDirection> directions = directionsInts.stream()
                .map(x -> MoveDirection.valueOf(directionsStrings[x]))
                .toList();


        // simulation1 is for 4 animals
        ConsoleMapDisplay observer1 = new ConsoleMapDisplay();
        worldMap1.addObserver(observer1);
        Simulation simulation1 = new Simulation(directions, positions, worldMap1);
        simulation1.run();

        worldMap1.removeObserver(observer1);

        // check if observer works properly
        assertEquals(34, observer1.getChangesCounter());

        List<Animal> animals1 = simulation1.getAnimals();

        assertTrue(animals1.get(0).isAt(new Vector2d(4, 3)));
        assertTrue(animals1.get(1).isAt(new Vector2d(3, 4)));
        assertTrue(animals1.get(2).isAt(new Vector2d(0, 0)));
        assertTrue(animals1.get(3).isAt(new Vector2d(2, 1)));


        // simulation2 is for 1 animal
        AbstractWorldMap worldMap2 = new RectangularMap(4, 4);

        ConsoleMapDisplay observer2 = new ConsoleMapDisplay();
        worldMap2.addObserver(observer2);
        Simulation simulation2 = new Simulation(directions, List.of(new Vector2d(2, 2)), worldMap2);
        simulation2.run();

        worldMap2.removeObserver(observer2);

        // check if observer works properly
        assertEquals(28, observer2.getChangesCounter());

        List<Animal> animals2 = simulation2.getAnimals();

        assertTrue(animals2.get(0).isAt(new Vector2d(4, 3)));

        // simulation3 is for empty list
        AbstractWorldMap worldMap3 = new RectangularMap(4, 4);

        ConsoleMapDisplay observer3 = new ConsoleMapDisplay();
        worldMap3.addObserver(observer3);
        Simulation simulation3 = new Simulation(directions, List.of(), worldMap3);
        simulation3.run();

        worldMap3.removeObserver(observer3);

        // check if observer works properly
        assertEquals(0, observer3.getChangesCounter());

        List<Animal> animals3 = simulation3.getAnimals();

        assertTrue(animals3.isEmpty());

        // simulation4 is for 2 animals that cant move because they face each other
        AbstractWorldMap worldMap4 = new RectangularMap(4, 4);

        ConsoleMapDisplay observer4 = new ConsoleMapDisplay();
        worldMap4.addObserver(observer4);

        directions = List.of(MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD);

        positions = List.of(new Vector2d(1, 1), new Vector2d(1, 2));

        Simulation simulation4 = new Simulation(directions, positions, worldMap4);
        simulation4.run();

        worldMap4.removeObserver(observer4);

        // check if observer works properly only counts placing animals on the map
        assertEquals(2, observer4.getChangesCounter());
    }

}
