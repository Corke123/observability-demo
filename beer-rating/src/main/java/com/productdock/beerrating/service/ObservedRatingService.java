package com.productdock.beerrating.service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObservedRatingService implements RatingService {

    private final RatingService delegate;
    private final ObservationRegistry observationRegistry;

    @Override
    public Double rate(long beerId, int rating) {
        return Observation.createNotStarted("rate.beer", observationRegistry)
                .lowCardinalityKeyValue("beerId", String.valueOf(beerId))
                .observe(() -> delegate.rate(beerId, rating));
    }
}
