package org.example;

public class Main {
    public static void main(String[] args) {

        String[][] array = {{"1", "2", "3", "4"},
                            {"2", "3", "4", "5"},
                            {"3", "4", "5", "6"},
                            {"4", "5", "6", "7"}};
//        String[][] array = {{"1", "3", "4"},
//                            {"2", "3", "4", "5"},
//                            {"3", "4", "5"}};
//        String[][] array = {{"1", "array", "3", "4"},
//                            {"2", "with", "4", "5"},
//                            {"3", "unexpected", "5", "6"},
//                            {"4", "data format", "6", "7"}};
        try {
            System.out.println("The sum of all numbers contained in the array is " + arrayProcessing(array));
        } catch (MyArraySizeException | MyArrayDataException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("End of process.");
        }
    }


    static int arrayProcessing(String[][] incomeArray) {
        int totalSum = 0;
        for (String[] innerArray : incomeArray) {
            if (incomeArray.length != 4) throw new MyArraySizeException("The array have to be 4 rows by 4 cells (!)");
            for (String str : innerArray) {
                if (innerArray.length != 4)
                    throw new MyArraySizeException("The array have to be 4 rows by 4 cells (!)");
                try {
                    totalSum += Integer.parseInt(str);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException("Array's cells have to contains numbers only (!)");
                }
            }
        }
        return totalSum;
    }
}

    class MyArraySizeException extends ArrayStoreException {
        public MyArraySizeException(String message) {
            super(message);
        }
    }

    class MyArrayDataException extends NumberFormatException {
        public MyArrayDataException(String message) {
            super(message);
        }
    }