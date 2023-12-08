package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.UUID;
import java.util.function.BiPredicate;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        super(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        BiPredicate<Integer, Integer> checkConstraints =
                (x, y) -> (x >= 0 && x <= width) && (y >= 0 && y <= height);
        return checkConstraints.test(position.getX(), position.getY()) && !isOccupied(position);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        super.place(animal);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal, direction);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return super.objectAt(position);
    }
}
