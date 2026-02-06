package com.viscovich.customer_service.customers.service;

import com.viscovich.customer_service.common.exception.EmailAlreadyExistsException;
import com.viscovich.customer_service.common.exception.ResourceNotFoundException;
import com.viscovich.customer_service.customers.model.Customer;
import com.viscovich.customer_service.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        if (repository.existsByEmail(customer.getEmail())) {
            throw new EmailAlreadyExistsException(customer.getEmail());
        }
        return repository.save(customer);
    }

    public Optional<Customer> getCustomer(Long customerId) {
        return Optional.of(repository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un cliente con el ID " + customerId)));
    }
}
