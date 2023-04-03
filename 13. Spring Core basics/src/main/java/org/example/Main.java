package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = annotationConfigApplicationContext.getBean("productService", ProductService.class);
        OrderService orderService = annotationConfigApplicationContext.getBean("orderService", OrderService.class);
        Cart cart = annotationConfigApplicationContext.getBean("cart", Cart.class);

        productService.printAll();
        System.out.println(productService.findProductByName("Bread"));
        cart.addProduct(productService.findProductByName("Milk"));
        orderService.orderFormation();

        annotationConfigApplicationContext.close();
    }
}