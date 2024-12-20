package com.productdock.beerrating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "beer", configuration = FeignClientConfig.class)
public interface BeerClient {

    @GetMapping("/beers/{beerId}/validate")
    BeerValidationResponse validate(@PathVariable long beerId);

    record BeerValidationResponse(long id, String name) {
    }
}
