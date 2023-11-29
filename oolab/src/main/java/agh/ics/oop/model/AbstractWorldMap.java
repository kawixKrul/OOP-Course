package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AbstractWorldMap implements WorldMap{
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    final int width;
    final int height;
    final List<MapChangeListener> observers;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.observers = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        this.place(animal, "Animal placed at " + animal.getPosition());
    }

    private void place(Animal animal, String message) throws PositionAlreadyOccupiedException {
        Vector2d position = animal.getPosition();
        if (!isOccupied(position) || !(objectAt(position) instanceof Animal)) {
            animals.put(position, animal);
            mapChanged(message);
        } else {
            throw new PositionAlreadyOccupiedException(position);
        }
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        MapDirection oldOrientation = animal.getOrientation();
        animal.move(direction, this);
        try {
            if (!oldPosition.equals(animal.getPosition())) {
                animals.remove(oldPosition);
                place(animal, "Animal moved from " + oldPosition + " to " + animal.getPosition());
            } else if (oldOrientation != animal.getOrientation()) {
                mapChanged("Animal at " + animal.getPosition() + "changed orientation from " + oldOrientation + " to " + animal.getOrientation());
            }
        } catch (PositionAlreadyOccupiedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return List.copyOf(animals.values());
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(width, height));
    }

    @Override
    public final String toString() {
        return new MapVisualizer(this)
                .draw(getCurrentBounds().lowerLeft(), getCurrentBounds().upperRight());
    }

    public final void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public final void removeObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public final void mapChanged(String message) {
        observers.forEach(observer -> observer.mapChanged(this, message));
    }
}
