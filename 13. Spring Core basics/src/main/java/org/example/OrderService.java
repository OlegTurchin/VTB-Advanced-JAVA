package org.example;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private Cart cart;

    public void orderFormation(){
        cart.getAddedProducts().forEach(System.out::println);
        System.out.println(cart.getAddedProducts().stream().mapToDouble(Product::getCost).sum());
    }
}
