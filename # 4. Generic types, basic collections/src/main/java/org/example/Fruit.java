package org.example;

abstract class Fruit {
    float weight;

    Fruit(float weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit {
    Apple() {
        super(1.0f);
    }
}

class Orange extends Fruit {
    Orange() {
        super(1.5f);
    }
}