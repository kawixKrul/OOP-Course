package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractWorldMap implements WorldMap{
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    final int width;
    final int height;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!isOccupied(position) || !(objectAt(position) instanceof Animal)) {
            animals.put(position, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        place(animal);
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
    public String toString() {
        return new MapVisualizer(this)
                .draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
