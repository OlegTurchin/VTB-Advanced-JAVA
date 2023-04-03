package org.example;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private final int speed;
    private final String name;
    private final CyclicBarrier cyclicBarrier;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Participant #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " is preparing himself.");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " is ready.");
            cyclicBarrier.await();
            cyclicBarrier.await();
            for (int i = 0; i < race.getStages().size(); i++) race.getStages().get(i).go(this);
            if (!Main.haveWinner.getAndSet(true)) {
                System.out.println("IMPORTANT ANNOUNCEMENT >>> We have a winner! " + this.getName() + " won the race!");
            }
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}