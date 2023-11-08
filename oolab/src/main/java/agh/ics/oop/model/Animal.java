package agh.ics.oop.model;

import java.util.function.BiPredicate;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public Animal() {
        this(new Vector2d(2,2));
    }

    @Override
    public String toString() {
        return orientation.toString() + position.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        BiPredicate<Integer, Integer> checkConstraints = (x, y) -> (x >= 0 && x <= 4) && (y >= 0 && y <= 4);
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (checkConstraints.test(newPosition.getX(), newPosition.getY())) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (checkConstraints.test(newPosition.getX(), newPosition.getY())) {
                    position = newPosition;
                }
            }
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
