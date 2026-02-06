package com.viscovich.product_service.products.repository;
import com.viscovich.product_service.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
