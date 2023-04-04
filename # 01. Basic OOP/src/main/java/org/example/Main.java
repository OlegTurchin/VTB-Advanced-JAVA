package org.example;

import org.example.Animals.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Bobik", 150,50));
        animals.add(new Dog("Rex", 170,65));
        animals.add(new Cat("Barsik", 50,0));
        animals.add(new Cat("Venchik", 30,0));
        animals.add(new Fish("Noname random fish #1", 0,70));
        animals.add(new Fish("Noname random fish #2", 0,120));
        animals.add(new Tiger("Smokey", 250,80));
        animals.add(new Tiger("River", 310,70));

        for (Animal o: animals) {
            o.run((int) (Math.random() * 310));
            o.swim((int) (Math.random() * 120));
        }

        animalCountResults();
    }

    static void animalCountResults(){
        System.out.println(Animal.animalCreationCount + " animals participated. They ran " + Animal.animalRunDistanceCount +
                "m. and swam " + Animal.animalSwimDistanceCount + "m. in total");
        System.out.println(Dog.getDogCount() + " dogs participated. They ran " + Dog.getDogRunDistanceCount() +
                "m. and swam " + Dog.getDogSwimDistanceCount() + "m. in total");
        System.out.println(Cat.getCatCount() + " cats participated. They ran " + Cat.getCatRunDistanceCount() +
                "m. and swam " + Cat.getCatSwimDistanceCount() + "m. in total");
        System.out.println(Fish.getFishCount() + " fishes participated. They ran " + Fish.getFishRunDistanceCount() +
                "m. and swam " + Fish.getFishSwimDistanceCount() + "m. in total");
        System.out.println(Tiger.getTigerCount() + " tigers participated. They ran " + Tiger.getTigerRunDistanceCount() +
                "m. and swam " + Tiger.getTigerSwimDistanceCount() + "m. in total");
    }
}