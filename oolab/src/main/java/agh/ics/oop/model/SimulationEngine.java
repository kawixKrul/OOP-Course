package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threads = new LinkedList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runAsync() {
        for (Simulation simulation: simulations) {
            threads.add(new Thread(simulation));
        }
        for (Thread thread: threads) {
            thread.start();
        }
    }

    public void runSync() {
        for (Simulation simulation: simulations) {
            simulation.run();
        }
    }

    public void awaitSimulationEnd() {
        try {
            if (threads.isEmpty()) {
                boolean succes = executorService.awaitTermination(10, TimeUnit.SECONDS);
            } else {
                for (Thread thread: threads) {
                    thread.join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runAsyncInThreadPool() {
        for (Simulation simulation: simulations) {
            executorService.execute(simulation);
        }
        executorService.shutdown();
    }
}
