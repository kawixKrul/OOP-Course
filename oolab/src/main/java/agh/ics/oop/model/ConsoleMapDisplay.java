package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int changesCounter = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap);
        System.out.println("Number of changes: " + ++changesCounter);
    }

    public int getChangesCounter() {
        return changesCounter;
    }
}
