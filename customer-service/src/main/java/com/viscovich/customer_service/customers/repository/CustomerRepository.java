package com.viscovich.customer_service.customers.repository;

import com.viscovich.customer_service.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
