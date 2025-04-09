package com.productdock.beerrating.config;

import com.productdock.beerrating.client.BeerCatalog;
import com.productdock.beerrating.repository.RatingRepository;
import com.productdock.beerrating.service.DefaultRatingService;
import com.productdock.beerrating.service.ObservedRatingService;
import com.productdock.beerrating.service.RatingService;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingServiceConfig {

    @Bean
    public RatingService ratingService(RatingRepository ratingRepository, BeerCatalog beerCatalog, ObservationRegistry registry) {
        return new ObservedRatingService(new DefaultRatingService(ratingRepository, beerCatalog), registry);
    }
}
