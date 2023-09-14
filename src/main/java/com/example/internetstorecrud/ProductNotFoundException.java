package com.example.internetstorecrud;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String exception) {
        super(exception);
    }
}
