package com.viscovich.order_service.orders.service;

import com.viscovich.order_service.customers.model.Customer;
import com.viscovich.order_service.orders.model.Order;
import com.viscovich.order_service.products.model.Product;
import com.viscovich.order_service.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Long customerId, List<Long> productIds, Order order) {
        String customersUrl = "http://localhost:8081/api/customers/" + customerId;
        String productsUrl = "http://localhost:8082/api/products";

        try {
            Customer customer = restTemplate.getForObject(customersUrl, Customer.class);

            if (customer == null) {
                throw new RuntimeException("El cliente no existe en el Customer-Service");
            }

            List<Product> products = new ArrayList<>();
            for (Long id : productIds){
                Product product = restTemplate.getForObject(productsUrl + "/" + id, Product.class);
                products.add(product);
            }

            if(products.isEmpty()){
                throw new RuntimeException("Una órden debe contener al menos un producto válido.");
            }

            for (Product product : products) {
                if(product.getStock() < 1) {
                    throw new RuntimeException("No hay stock suficiente del producto: " + product.getName());
                }
            }

            BigDecimal total = BigDecimal.ZERO;
            for (Product product : products) {
                product.setStock(product.getStock() - 1);
                restTemplate.put(productsUrl + "/" + product.getId() + "/reduce-stock", null);
                total = total.add(product.getPrice());
            }

            order.setCustomerId(customer.getId());
            order.setProductIds(productIds);
            order.setTotal(total);
            order.setDate(LocalDateTime.now());
            return orderRepository.save(order);

        } catch (Exception e) {
            throw new RuntimeException("Error en la comunicación entre servicios: " + e.getMessage());
        }
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        String url = "http://localhost:8081/api/customers/" + customerId;

        try {
            Customer customer = restTemplate.getForObject(url, Customer.class);

            if (customer == null) {
                throw new RuntimeException("El cliente no existe en el Customer-Service");
            }
            return orderRepository.findByCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Error en la comunicación entre servicios: " + e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
