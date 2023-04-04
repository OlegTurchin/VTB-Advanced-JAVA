package org.example.Animals;

public class Tiger extends Animal {
    private static int tigerCount;
    private static int tigerRunDistanceCount;
    private static int tigerSwimDistanceCount;

    public static int getTigerRunDistanceCount() {
        return tigerRunDistanceCount;
    }

    public static int getTigerSwimDistanceCount() {
        return tigerSwimDistanceCount;
    }

    public Tiger(String name, int maxRunLength, int maxSwimLength) {
        super(name, maxRunLength, maxSwimLength);
        tigerCount++;
    }

    public static int getTigerCount() {
        return tigerCount;
    }

    @Override
    public void run(int requiredLength) {
        super.run(requiredLength);
        if(maxRunLength >= requiredLength){
            tigerRunDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
    @Override
    public void swim(int requiredLength) {
        super.swim(requiredLength);
        if(maxSwimLength >= requiredLength){
            tigerSwimDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
}
