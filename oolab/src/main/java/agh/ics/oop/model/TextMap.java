package agh.ics.oop.model;


import java.util.ArrayList;
import java.util.Collections;

public class TextMap<Integer, String> implements WorldMap<Integer, String> {
    private int size = 0;
    private final ArrayList<String> map;

    public TextMap(ArrayList<String> map) {
        this.map = map;
        this.size = map.size();
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        int x = position.getX();
        return x >= 0 && x < size;
    }

    @Override
    public boolean place(String animal) {
        map.add(animal);
        size++;
        return true;
    }

    @Override
    public void move(String animal, MoveDirection direction) {
        switch (direction) {
            case FORWARD -> System.out.println("Ignoring move forward");
            case BACKWARD -> System.out.println("Ignoring move backward");
            case RIGHT -> {
                Vector2d position = new Vector2d(map.indexOf(animal)+1, 0);
                if (canMoveTo(position)) {
                    int x = position.getX();
                    Collections.swap(map, map.indexOf(animal), x);
                }
            }
            case LEFT -> {
                Vector2d position = new Vector2d(map.indexOf(animal)-1, 0);
                if (canMoveTo(position)) {
                    int x = position.getX();
                    Collections.swap(map, map.indexOf(animal), x);
                }
            }
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        if (canMoveTo(new Vector2d((int)position, 0))) {
            return map.get((int)position) != null;
        }
        return false;
    }

    @Override
    public String objectAt(Integer position) {
        if (canMoveTo(new Vector2d((int)position, 0))) {
            return map.get((int)position);
        }
        return null;
    }

    public void printMap() {
        map.forEach(System.out::println);
    }
}
