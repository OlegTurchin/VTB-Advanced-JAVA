package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Object[] array = new Object[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(array));

        arrayCellsSwap(array, 2, 7);

        System.out.println(arrayToArrayList(array));

        Box<Orange> boxWithFruits1 = new Box<>(new Orange(), new Orange(), new Orange(), new Orange(), new Orange());
        Box<Apple> boxWithFruits2 = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple());
        Box<Apple> boxWithFruits3 = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());

        System.out.println(boxWithFruits1.getWeight() + "f");
        System.out.println(boxWithFruits2.getWeight() + "f");
        System.out.println(boxWithFruits3.getWeight() + "f");

        System.out.println(boxWithFruits2.boxCompare(boxWithFruits3));
        boxWithFruits2.addAFruitToTheBox(new Apple());
        System.out.println(boxWithFruits2.boxCompare(boxWithFruits3));
        boxWithFruits2.addAFruitToTheBox(new Apple(), new Apple());
//        boxWithFruits1.mergeBoxes(boxWithFruits2);
        System.out.println(boxWithFruits2.getWeight());
    }

    static boolean arrayCellsSwap(Object[] incomeArray, int firstCellIndex, int secondCellIndex) {
        try {
            Object swapVariable = incomeArray[firstCellIndex];
            incomeArray[firstCellIndex] = incomeArray[secondCellIndex];
            incomeArray[secondCellIndex] = swapVariable;
            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    static <T> ArrayList<T> arrayToArrayList(T[] incomeArray) {
        return new ArrayList<T>(List.of(incomeArray));
    }
}