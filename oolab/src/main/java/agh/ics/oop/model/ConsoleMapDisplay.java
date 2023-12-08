package agh.ics.oop.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleMapDisplay implements MapChangeListener{
    private final AtomicInteger changesCounter = new AtomicInteger(0);

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println(message);
        System.out.println(worldMap.getId());
        System.out.println(worldMap);
        System.out.println("Number of changes: " + changesCounter.getAndIncrement());
    }

    public int getChangesCounter() {
        return changesCounter.get();
    }
}
