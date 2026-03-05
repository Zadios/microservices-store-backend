package com.viscovich.customer_service.customers.controller;

import com.viscovich.customer_service.customers.model.Customer;
import com.viscovich.customer_service.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Customer> allCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable("id") Long id) {
        return service.getCustomer(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@Valid @RequestBody Customer newCustomer){
        return service.saveCustomer(newCustomer);
    }
}
