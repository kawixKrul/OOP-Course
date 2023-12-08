package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.LinkedList;
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
            AbstractWorldMap worldMap1 = new GrassField(10);
            AbstractWorldMap worldMap2 = new RectangularMap(5, 5);
            worldMap1.addObserver(new ConsoleMapDisplay());
            worldMap2.addObserver(new ConsoleMapDisplay());
            SimulationEngine simulation = new SimulationEngine(
                    List.of(
                            new Simulation(directions, positions, worldMap1),
                            new Simulation(directions, positions, worldMap2)
                    )
            );
            simulation.runAsyncInThreadPool();
            simulation.awaitSimulationEnd();
            /*ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();
            List<Simulation> sims = new LinkedList<>();
            for (int i = 0; i < 1000; i++) {
                AbstractWorldMap worldMap = new GrassField(10);
                worldMap.addObserver(consoleMapDisplay);
                sims.add(new Simulation(directions, positions, worldMap));
            }
            SimulationEngine simulation = new SimulationEngine(sims);
            simulation.runAsync();
            simulation.awaitSimulationEnd();*/

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            exit(1);
        } finally {
            System.out.println("Stop");
        }

    }
}
