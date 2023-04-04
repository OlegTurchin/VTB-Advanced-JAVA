package org.example.Animals;

public class Dog extends Animal {
    private static int dogCount;
    private static int dogRunDistanceCount;
    private static int dogSwimDistanceCount;

    public static int getDogRunDistanceCount() {
        return dogRunDistanceCount;
    }

    public static int getDogSwimDistanceCount() {
        return dogSwimDistanceCount;
    }

    public Dog(String name, int maxRunLength, int maxSwimLength) {
        super(name, maxRunLength, maxSwimLength);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    @Override
    public void run(int requiredLength) {
        super.run(requiredLength);
        if(maxRunLength >= requiredLength){
            dogRunDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
    @Override
    public void swim(int requiredLength) {
        super.swim(requiredLength);
        if(maxSwimLength >= requiredLength){
            dogSwimDistanceCount += requiredLength;
            requiredLength = 0;
        }
    }
}
