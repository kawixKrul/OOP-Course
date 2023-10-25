package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        MoveDirection[] result = new MoveDirection[args.length];
        int count = 0;
        for(String arg: args) {
            switch (arg) {
                case "f" -> result[count++] = MoveDirection.FORWARD;
                case "b" -> result[count++] = MoveDirection.BACKWARD;
                case "l" -> result[count++] = MoveDirection.LEFT;
                case "r" -> result[count++] = MoveDirection.RIGHT;
                default -> {}
            }
        }
        return Arrays.copyOf(result, count);
    }
}
