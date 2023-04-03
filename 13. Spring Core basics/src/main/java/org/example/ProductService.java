package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class ProductService {

    private List<Product> products;

    public void printAll(){
        for (Product p: products) {
            System.out.println("Product: " + p.getTitle() + " by price: " + p.getCost() + "$");
        }
    }

    public Product findProductByName(String name){
        return products.stream().filter(product -> product.getTitle().equals(name)).findAny().get();
    }

    @PostConstruct
    private void initialization() {
        products = new ArrayList<Product>();

        products.add(new Product(1, "Soap", 2.50));
        products.add(new Product(2, "Bread", 1.50));
        products.add(new Product(3, "Coffee", 4.50));
        products.add(new Product(4, "Dumplings", 3.50));
        products.add(new Product(5, "Potato", 0.50));
        products.add(new Product(6, "Bubblegum", 0.50));
        products.add(new Product(7, "Sneaks", 1.50));
        products.add(new Product(8, "Milk", 2.50));
        products.add(new Product(9, "Pepper", 0.50));
        products.add(new Product(10, "Tea", 3.50));
    }
}

@Data
@AllArgsConstructor
class Product {

    private int id;

    private String title;

    private double cost;

    @Override
    public String toString(){
        return "Product: " + this.getTitle() + " by price: " + this.getCost() + "$";
    }
}
