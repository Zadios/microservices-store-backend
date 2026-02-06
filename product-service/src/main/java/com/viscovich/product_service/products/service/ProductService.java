package com.viscovich.product_service.products.service;
import com.viscovich.product_service.common.exception.InsufficientStockException;
import com.viscovich.product_service.common.exception.ResourceNotFoundException;
import com.viscovich.product_service.products.model.Product;
import com.viscovich.product_service.products.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long productId){
        return Optional.of(repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un porducto con el ID: " + productId)));
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public void reduceStock(Long productId){
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el ID: " + productId));

        if(product.getStock()<1) {
            throw new InsufficientStockException("No hay stock suficiente del producto: " + product.getName());
        }
        product.setStock(product.getStock() - 1);
        repository.save(product);
    }
}
