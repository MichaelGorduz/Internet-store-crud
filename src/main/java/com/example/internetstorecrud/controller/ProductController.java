package com.example.internetstorecrud.controller;

import com.example.internetstorecrud.ProductNotFoundException;
import com.example.internetstorecrud.model.Product;
import com.example.internetstorecrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product retrieveProduct(@PathVariable long productid) {
        Optional<Product> product = productRepository.findById(productid);

        if (!product.isPresent())
            throw new ProductNotFoundException("id-" + productid);

        return product.get();
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long productid) {
        productRepository.deleteById(productid);
    }

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getProductid()).toUri();

        return ResponseEntity.created(location).build();
    }
    @GetMapping("/products/price")
    public List<Product> retrieveAllProductsSortedByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long productid) {

        Optional<Product> productOptional = productRepository.findById(productid);

        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        product.setProductid(productid);
        productRepository.save(product);
        return ResponseEntity.noContent().build();
    }
}
