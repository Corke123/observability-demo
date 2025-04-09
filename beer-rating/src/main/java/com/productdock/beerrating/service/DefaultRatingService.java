package com.productdock.beerrating.service;

import com.productdock.beerrating.client.BeerCatalog;
import com.productdock.beerrating.model.BeerRating;
import com.productdock.beerrating.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class DefaultRatingService implements RatingService {

    private final RatingRepository ratingRepository;
    private final BeerCatalog beerCatalog;

    @Override
    @Transactional
    public Double rate(long beerId, int rating) {
        log.info("Rate beerId={} rating={}", beerId, rating);
        beerCatalog.getBeer(beerId);

        BeerRating beerRating = BeerRating.builder().beerId(beerId).rating(rating).build();
        ratingRepository.save(beerRating);
        return ratingRepository.findAverageRatingByBeerId(beerId);
    }
}
