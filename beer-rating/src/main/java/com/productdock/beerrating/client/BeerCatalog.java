package com.productdock.beerrating.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface BeerCatalog {

    @GetExchange("/beers/{beerId}")
    void getBeer(@PathVariable long beerId);
}
