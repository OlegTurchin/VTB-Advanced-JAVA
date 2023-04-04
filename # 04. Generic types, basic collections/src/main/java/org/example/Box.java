package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> boxContent;

    Box(T... fruits) {
        this.boxContent = new ArrayList<>(Arrays.asList(fruits));
    }

    float getWeight() {
        return (this.boxContent.size() * this.boxContent.get(0).weight);
    }

    boolean boxCompare(Box<?> boxToCompareTo) {
        return this.getWeight() == boxToCompareTo.getWeight();
    }

    void addAFruitToTheBox(T fruit) {
        this.boxContent.add(fruit);
    }

    void addAFruitToTheBox(T... fruit) {
        this.boxContent.addAll(Arrays.asList(fruit));
    }

    void mergeBoxes(Box<? super T> boxToMergeWith) {
        boxToMergeWith.boxContent.addAll(this.boxContent);
        this.boxContent.clear();
    }
}
