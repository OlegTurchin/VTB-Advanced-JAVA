package org.example;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = length + " meters tunnel.";
    }

    final Semaphore semaphore = new Semaphore(Main.CARS_COUNT / 2);

    @Override
    public void go(Car c) {
        try {
            try {
                if (!semaphore.tryAcquire()) {
                    System.out.println(c.getName() + " waiting to enter: " + description);
                    semaphore.acquire();
                }
                System.out.println(c.getName() + " started to pass the stage: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(c.getName() + " passed the stage: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}