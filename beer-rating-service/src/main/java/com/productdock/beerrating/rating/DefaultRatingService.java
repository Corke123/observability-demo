package com.productdock.beerrating.rating;

import com.productdock.beerrating.client.BeerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
public class DefaultRatingService implements RatingService {

    private final RatingRepository ratingRepository;
    private final BeerClient beerClient;

    @Override
    @Transactional
    public double rate(long beerId, int rating) {
        log.info("Rate beerId={} rating={}", beerId, rating);
        beerClient.validate(beerId);

        BeerRating beerRating = BeerRating.builder().beerId(beerId).rating(rating).build();
        ratingRepository.save(beerRating);
        return ratingRepository.findAverageRatingByBeerId(beerId);
    }
}
