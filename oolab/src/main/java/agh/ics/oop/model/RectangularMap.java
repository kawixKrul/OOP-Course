package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class RectangularMap implements WorldMap{
    private final int width;
    private final int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        BiPredicate<Integer, Integer> checkConstraints =
                (x, y) -> (x >= 0 && x <= width) && (y >= 0 && y <= height);
        return checkConstraints.test(position.getX(), position.getY()) && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (isOccupied(position)) {
            return false;
        } else {
            animals.put(position, animal);
            return true;
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this)
                .draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
