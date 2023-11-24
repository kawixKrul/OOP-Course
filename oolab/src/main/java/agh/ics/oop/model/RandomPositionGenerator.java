package agh.ics.oop.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    final int width;
    final int height;
    final int grassNumber;

    public RandomPositionGenerator(int width, int height, int grassNumber) {
        this.width = width;
        this.height = height;
        this.grassNumber = grassNumber;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new VectorIterator();
    }

    private class VectorIterator implements Iterator<Vector2d> {
        private int idx = 0;
        private List<Vector2d> cords;

        private VectorIterator() {
            cords = IntStream.range(0, width)
                    .mapToObj(x -> IntStream.range(0, height)
                            .mapToObj(y -> new Vector2d(x, y)))
                    .flatMap(x -> x)
                    .collect(Collectors.toList());

            long seed = System.nanoTime();
            Collections.shuffle(cords, new Random(seed));
        }


        @Override
        public boolean hasNext() {
            return idx < grassNumber;
        }

        @Override
        public Vector2d next() {
            return cords.get(idx++);
        }
    }
}

