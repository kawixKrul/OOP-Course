package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.List;

public class Simulation {
    private final List<MoveDirection> directionList;
    private final List<Animal> animalList;

    public Simulation(List<MoveDirection> directionList, List<Vector2d> positions) {
        this.directionList = directionList;
        this.animalList = positions.stream()
                .map(Animal::new)
                .toList();
    }

    public void run() {
        int size = animalList.size();
        if (size == 0) {
            return;
        }
        int index = 0;
        for (MoveDirection direction: directionList) {
            Animal currentAnimal = animalList.get(index % size);
            currentAnimal.move(direction);
            System.out.println("Zwierze " + index++ % size + ": " + currentAnimal);
        }
    }

    public List<Animal> getAnimals() {
        return this.animalList;
    }
}
