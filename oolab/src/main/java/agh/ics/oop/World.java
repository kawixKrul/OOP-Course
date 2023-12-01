package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

import static java.lang.System.exit;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(
                    new Vector2d(2,2),
                    new Vector2d(7,8)
            );
            AbstractWorldMap worldMap = new GrassField(10);
            worldMap.addObserver(new ConsoleMapDisplay());
            Simulation simulation = new Simulation(directions, positions, worldMap);
            simulation.run();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            exit(1);
        } finally {
            System.out.println("Stop");
        }

    }
}
