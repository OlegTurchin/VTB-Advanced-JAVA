package org.example;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class Cart {

    @Autowired
    private ProductService productService;

    private List<Product> addedProducts;

    public void addProduct(Product product){
        addedProducts.add(product);
    }

    @PostConstruct
    private void initialization(){
        addedProducts = new ArrayList<Product>();

        for (int i = 0; i < 3; i++) {
            addedProducts.add(productService.getProducts().get((int)(Math.random()*10) + 1));
        }
    }

}
