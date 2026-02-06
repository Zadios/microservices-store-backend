package com.viscovich.order_service.customers.model;

import lombok.Data;


@Data
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}

