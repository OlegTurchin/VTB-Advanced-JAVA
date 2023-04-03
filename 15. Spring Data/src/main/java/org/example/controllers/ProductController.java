package org.example.controllers;

import org.example.entities.Product;
import org.example.repositories.specifications.ProductSpecification;
import org.example.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public String showProductList(Model model,
                                  @RequestParam(value = "word", required = false) String word,
                                  @RequestParam(value = "minPrice", required = false) Integer minPrice,
                                  @RequestParam(value = "maxPrice", required = false) Integer maxPrice
    ) {
        Specification<Product> productSpecification = Specification.where(null);

        if (word != null) productSpecification = productSpecification.and(ProductSpecification.titleContains(word));
        if (minPrice != null) productSpecification = productSpecification.and(ProductSpecification.priceGreaterThenOrEqualTo(minPrice));
        if (maxPrice != null) productSpecification = productSpecification.and(ProductSpecification.priceLessThenOrEqualTo(maxPrice));

        model.addAttribute("products", productService.getProductsWithPagingAndFiltering(productSpecification, PageRequest.of(0, 10)).getContent());
        model.addAttribute("word", word);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);

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
        productService.saveOrUpdateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update_product/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "update_product";
    }

    @PostMapping("/update")
    public String saveUpdatedProduct(@ModelAttribute(value = "product") Product product) {
        productService.saveOrUpdateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/remove_product/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.removeProduct(id);
        return "redirect:/products";
    }
}