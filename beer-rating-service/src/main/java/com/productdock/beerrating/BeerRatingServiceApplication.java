package com.productdock.beerrating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BeerRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerRatingServiceApplication.class, args);
    }

}
