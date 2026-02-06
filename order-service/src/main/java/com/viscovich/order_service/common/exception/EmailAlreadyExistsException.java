package com.viscovich.order_service.common.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("El Email '" + email + "' ya está registrado.");
    }
}
