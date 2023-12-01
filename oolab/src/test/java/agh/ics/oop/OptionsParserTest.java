package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class OptionsParserTest {

    @Test
    public void parse() {
        String[] args = {"f", "b", "r", "l"};
        MoveDirection[] correctDirections = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        };
        List<MoveDirection> directions = OptionsParser.parse(args);
        // Test for the equality of arrays content
        for (int i=0; i<directions.size(); i++) {
            assertEquals(correctDirections[i], directions.get(i));
        }
        // Test for the length of the array
        assertEquals(4, directions.size());

        // test for ecxeption
        String[] args2 = {"f", "b", "r", "l", "x"};
        try {
            OptionsParser.parse(args2);
        } catch (Exception e) {
            assertInstanceOf(e.getClass(), new IllegalArgumentException());
        }
    }
}
