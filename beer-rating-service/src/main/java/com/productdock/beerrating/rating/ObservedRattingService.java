package com.productdock.beerrating.rating;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObservedRattingService implements RatingService {

    private final RatingService delegate;
    private final ObservationRegistry observationRegistry;

    @Override
    public double rate(long beerId, int rating) {
        return Observation.createNotStarted("rate.beer", observationRegistry)
                .lowCardinalityKeyValue("beerId", String.valueOf(beerId))
                .observe(() -> delegate.rate(beerId, rating));
    }
}
