package org.example.Animals;

public class Fish extends Animal {
    private static int fishCount;
    private static int fishRunDistanceCount;
    private static int fishSwimDistanceCount;

    public static int getFishRunDistanceCount() {
        return fishRunDistanceCount;
    }

    public static int getFishSwimDistanceCount() {
        return fishSwimDistanceCount;
    }

    public Fish(String name, int maxRunLength, int maxSwimLength) {
        super(name, maxRunLength, maxSwimLength);
        fishCount++;
    }

    public static int getFishCount() {
        return fishCount;
    }

    @Override
    public void run(int requiredLength) {
        super.run(requiredLength);
        if(maxRunLength >= requiredLength){
            fishRunDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
    @Override
    public void swim(int requiredLength) {
        super.swim(requiredLength);
        if(maxSwimLength >= requiredLength){
            fishSwimDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
}
