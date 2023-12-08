package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap {
    final int grassCount;
    final private Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        super((int)Math.sqrt(grassCount*10), (int)Math.sqrt(grassCount*10));
        this.grassCount = grassCount;
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
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        super.place(animal);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal, direction);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return super.objectAt(position) != null ? super.objectAt(position) : grasses.get(position);
    }

    @Override
    public List<WorldElement> getElements() {
        return Stream.concat(super.getElements().stream(), grasses.values().stream())
                .toList();
    }
}
