package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static final int CARS_COUNT = 4;
    static AtomicBoolean haveWinner = new AtomicBoolean(false);
    final static CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT + 1);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("IMPORTANT ANNOUNCEMENT >>> Preparations in progress!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier);
            new Thread(cars[i]).start();
        }
        try {
            cyclicBarrier.await();
            System.out.println("IMPORTANT ANNOUNCEMENT >>> The race has begun!!!");
            cyclicBarrier.await();
            cyclicBarrier.await();
            System.out.println("IMPORTANT ANNOUNCEMENT >>> The race is over!!!");
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}