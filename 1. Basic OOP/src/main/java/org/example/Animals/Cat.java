package org.example.Animals;

public class Cat extends Animal {
    private static int catCount;
    private static int catRunDistanceCount;

    public static int getCatRunDistanceCount() {
        return catRunDistanceCount;
    }

    public static int getCatSwimDistanceCount() {
        return catSwimDistanceCount;
    }

    private static int catSwimDistanceCount;

    public Cat(String name, int maxRunLength, int maxSwimLength) {
        super(name, maxRunLength, maxSwimLength);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    @Override
    public void run(int requiredLength) {
        super.run(requiredLength);
        if(maxRunLength >= requiredLength){
            catRunDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
    @Override
    public void swim(int requiredLength) {
        super.swim(requiredLength);
        if(maxSwimLength >= requiredLength){
            catSwimDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
}
