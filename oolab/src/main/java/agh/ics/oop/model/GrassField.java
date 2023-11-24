package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class GrassField implements WorldMap {
    final int grassCount;
    final Map<Vector2d, Animal> animals = new HashMap<>();
    final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        this.grassCount = grassCount;
        int width = (int)Math.sqrt(grassCount*10);
        int height = (int)Math.sqrt(grassCount*10);
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(width, height, grassCount);
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        return object == null || object instanceof Grass;
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
        return animals.containsKey(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Animal animal = animals.get(position);
        if (animal != null) {
            return animal;
        }
        return grasses.get(position);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this)
                .draw(
                        new Vector2d(0, 0),
                        new Vector2d((int)Math.sqrt(grassCount*10), (int)Math.sqrt(grassCount*10))
                );
    }
}
