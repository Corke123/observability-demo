package com.productdock.beerrating.client;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    Level feignLoggerLevel() {
        return Level.BASIC;
    }
}
