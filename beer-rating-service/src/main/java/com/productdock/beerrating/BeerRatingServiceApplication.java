package com.productdock.beerrating;

import com.productdock.beerrating.client.BeerClient;
import com.productdock.beerrating.rating.DefaultRatingService;
import com.productdock.beerrating.rating.ObservedRattingService;
import com.productdock.beerrating.rating.RatingRepository;
import com.productdock.beerrating.rating.RatingService;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BeerRatingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeerRatingServiceApplication.class, args);
    }

    @Bean
    public RatingService ratingService(RatingRepository ratingRepository, BeerClient beerClient, ObservationRegistry registry) {
        return new ObservedRattingService(new DefaultRatingService(ratingRepository, beerClient), registry);
    }
}
