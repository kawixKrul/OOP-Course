package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {

    @Test
    public void parse() {
        String[] args = {"f", "forward", "b", "backward", "r", "right", "l", "left"};
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
    }
}
