package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.*;

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

        WorldMap worldMap = new RectangularMap(100, 100);

        String[] directionsStrings = {"FORWARD", "BACKWARD", "RIGHT", "LEFT"};

        List<Integer> directionsInts = List.of(
                0,1,3,2,3,3,2,1,2,3,1,2,3,1,1,1,2,2,3,3,2,2,1,1,0,0,0,0,0,0,0,0,0,2,3,3,0
        );

        List<MoveDirection> directions = directionsInts.stream()
                .map(x -> MoveDirection.valueOf(directionsStrings[x]))
                .toList();


        // simulation1 is for 4 animals
        Simulation simulation1 = new Simulation(directions, positions, worldMap);
        simulation1.run();

        List<Animal> animals1 = simulation1.getAnimals();

        assertTrue(animals1.get(0).isAt(new Vector2d(4, 3)));
        assertTrue(animals1.get(1).isAt(new Vector2d(3, 4)));
        assertTrue(animals1.get(2).isAt(new Vector2d(0, 0)));
        assertTrue(animals1.get(3).isAt(new Vector2d(2, 1)));


        // simulation2 is for 1 animal
        Simulation simulation2 = new Simulation(directions, List.of(new Vector2d(2, 2)), worldMap);
        simulation2.run();

        List<Animal> animals2 = simulation2.getAnimals();

        assertTrue(animals2.get(0).isAt(new Vector2d(4, 3)));

        // simulation3 is for empty list
        Simulation simulation3 = new Simulation(directions, List.of(), worldMap);
        simulation3.run();

        List<Animal> animals3 = simulation3.getAnimals();

        assertTrue(animals3.isEmpty());
    }

}
