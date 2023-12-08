package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.exceptions.PositionAlreadyOccupiedException;

import java.util.List;

public class Simulation implements Runnable {
    private final List<MoveDirection> directionList;
    private final List<Animal> animalList;
    private final WorldMap worldMap;

    public Simulation(List<MoveDirection> directionList, List<Vector2d> positions, WorldMap worldMap) {
        this.directionList = directionList;
        this.animalList =  positions.stream()
                .map(Animal::new)
                .toList();
        this.worldMap = worldMap;
        animalList.forEach(animal -> {
            try {
                worldMap.place(animal);
            } catch (PositionAlreadyOccupiedException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void run() {
        int size = animalList.size();
        if (size == 0) {
            return;
        }
        int index = 0;
        for (MoveDirection direction: directionList) {
            Animal currentAnimal = animalList.get(index++%size);
            worldMap.move(currentAnimal, direction);
        }
    }

    public List<Animal> getAnimals() {
        return this.animalList;
    }
}
