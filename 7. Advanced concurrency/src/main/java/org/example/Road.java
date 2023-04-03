package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = length + " meters road.";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " started to pass the stage: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);
            System.out.println(c.getName() + " passed the stage: " + description);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}