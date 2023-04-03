package org.example.services;

import org.example.entities.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> getProductsWithPagingAndFiltering(Specification<Product> productSpecification, Pageable pageable){
        return productRepository.findAll(productSpecification, pageable);
    }

    public void saveOrUpdateProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}