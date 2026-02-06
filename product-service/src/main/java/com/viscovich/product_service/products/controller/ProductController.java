package com.viscovich.product_service.products.controller;

import com.viscovich.product_service.products.model.Product;
import com.viscovich.product_service.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> allProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id")Long id){
        return service.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody Product newProduct) {
        return service.saveProduct(newProduct);
    }

    @PutMapping("/{id}/reduce-stock")
    public void reduceStock(@PathVariable Long id) {
        service.reduceStock(id);
    }

}
