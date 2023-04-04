package org.example.Animals;

public abstract class Animal {
    String name;
    final int maxRunLength;
    final int maxSwimLength;
    public static int animalCreationCount;
    public static int animalRunDistanceCount;
    public static int animalSwimDistanceCount;

    public Animal(String name,int maxRunLength,int maxSwimLength) {
        this.name = name;
        this.maxRunLength = maxRunLength;
        this.maxSwimLength = maxSwimLength;
        animalCreationCount++;
    }

    public void run(int requiredLength){
        if(maxRunLength >= requiredLength){
            System.out.println(name + " has ran " + requiredLength+ " meters.");
            animalRunDistanceCount += requiredLength;
        } else if (maxRunLength == 0) {
            System.out.println(name + " cannot run");
        } else
            System.out.println(name + " has failed to run " + requiredLength+ " meters.");
    }
    public void swim(int requiredLength){
        if(maxSwimLength >= requiredLength){
            System.out.println(name + " has swam " + requiredLength+ " meters.");
            animalSwimDistanceCount += requiredLength;
        } else if (maxSwimLength == 0) {
            System.out.println(name + " cannot swim");
        } else
            System.out.println(name + " has failed to swim " + requiredLength+ " meters.");
    }
}

