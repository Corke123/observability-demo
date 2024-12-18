package com.productdock.beerservice.beer.exception;

public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(String message) {
        super(message);
    }
}
