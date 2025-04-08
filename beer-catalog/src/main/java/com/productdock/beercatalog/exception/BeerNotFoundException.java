package com.productdock.beercatalog.exception;

public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(String message) {
        super(message);
    }
}
