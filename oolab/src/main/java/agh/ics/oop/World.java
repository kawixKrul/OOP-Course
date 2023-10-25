package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void main(String[] args) {
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }

    public static void run(String[] args) {
        MoveDirection[] directions = OptionsParser.parse(args);
        for (MoveDirection direction : directions) {
            System.out.println(direction);
        }
    }
}
