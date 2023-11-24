package agh.ics.oop.model;

import java.util.function.BiPredicate;

public class RectangularMap extends AbstractWorldMap{

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
    public boolean place(Animal animal) {
        return super.place(animal);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
