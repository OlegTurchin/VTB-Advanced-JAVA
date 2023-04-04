package org.example.controllers;

import org.example.entities.Product;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        Product product = new Product();
        model.addAttribute(product);
        return "products";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute(value = "product") Product product) {
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update_product/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "update_product";
    }

    @PostMapping("/update")
    public String saveUpdatedProduct(@ModelAttribute(value = "product") Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/remove_product/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.removeProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/find_any")
    public String findAny(@RequestParam(value = "title") String title, Model model){
        model.addAttribute("products", productService.getFilteredListOfProducts(title));
        return "filtered_list_of_products";
    }

    @GetMapping("/close_filter")
    public String showFilteredProductList(Model model) {
        return "redirect:/products";
    }
}