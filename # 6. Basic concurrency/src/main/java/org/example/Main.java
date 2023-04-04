package org.example;

public class Main {
    static final int SIZE = 10_000_000;
    static float[] arr = new float[SIZE];
    static float[] arr1 = new float[SIZE];
    static float[] arr2 = new float[SIZE/2];
    static float[] arr3 = new float[SIZE/2];


    public static void main(String[] args) throws InterruptedException {
        arraysInitialization();

        for (int i = 0; i < 10; i++) {
            experimentWFullArray();
            experimentWSplitArray();
        }
    }
    static void arraysInitialization(){
        for (float f : arr) {
            f = 1.051f;
        }
        for (float f : arr1) {
            f = 1.051f;
        }
    }

    static void experimentWFullArray() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("The whole array processing take " + (System.currentTimeMillis() - a) + " sec.");
    }
    static void experimentWSplitArray() throws InterruptedException {
        long a = System.currentTimeMillis();
        System.arraycopy(arr,0,arr2,0,arr2.length);
        System.arraycopy(arr,arr.length/2,arr3,0,arr3.length);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < arr3.length; i++) {
                arr3[i] = (float) (arr3[i] * Math.sin(0.2f + (i+SIZE/2) / 5) * Math.cos(0.2f + (i+SIZE/2) / 5) * Math.cos(0.4f + (i+SIZE/2) / 2));
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.arraycopy(arr2, 0,arr1,0, arr2.length);
        System.arraycopy(arr3, 0,arr1,arr1.length/2, arr3.length);
        System.out.println("Splitting into 2 halves array processing takes " + (System.currentTimeMillis() - a) + " sec.");
    }
}