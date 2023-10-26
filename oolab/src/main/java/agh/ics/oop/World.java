package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        run(args);
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection direction = MapDirection.NORTH;
        System.out.println(direction);
        System.out.println(direction.next());
        System.out.println(direction.previous());
        System.out.println(direction.toUnitVector());
        System.out.println("Stop");
    }

    public static void run(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);
        for (MoveDirection direction : directions) {
            System.out.println(direction);
        }
    }
}
