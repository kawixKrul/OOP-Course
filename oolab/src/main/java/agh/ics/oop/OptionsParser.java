package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> result = new LinkedList<>();
        for(String arg: args) {
            switch (arg) {
                case "f" -> result.add(MoveDirection.FORWARD);
                case "b" -> result.add(MoveDirection.BACKWARD);
                case "l" -> result.add(MoveDirection.LEFT);
                case "r" -> result.add(MoveDirection.RIGHT);
                default -> {}
            }
        }
        return result;
    }
}
