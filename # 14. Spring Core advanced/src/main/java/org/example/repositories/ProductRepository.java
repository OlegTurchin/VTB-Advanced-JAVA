package org.example.repositories;

import org.example.entities.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    @PostConstruct
    public void initialization() {
        products = new ArrayList<>();

        products.add(new Product(1L, "Bread", 22));
        products.add(new Product(2L, "Milk", 18));
        products.add(new Product(3L, "Soap", 43));
        products.add(new Product(4L, "Coffee", 35));
        products.add(new Product(5L, "Dumplings", 53));
        products.add(new Product(6L, "Bubblegum", 12));
        products.add(new Product(7L, "Sneaks", 27));
        products.add(new Product(8L, "Milk", 17));
        products.add(new Product(9L, "Pepper", 4));
        products.add(new Product(10L, "Tea", 37));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findAny().get();
    }


    public void saveProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product product) {
        Product targetProduct = products.stream().filter(p -> p.getId().equals(product.getId())).findFirst().get();
        targetProduct.setTitle(product.getTitle());
        targetProduct.setPrice(product.getPrice());
    }

    public void removeProduct(Long id) {
        products.remove(products.stream().filter(product -> product.getId().equals(id)).findAny().get());
    }
}