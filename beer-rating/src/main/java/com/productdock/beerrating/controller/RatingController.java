package com.productdock.beerrating.controller;

import com.productdock.beerrating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("ratings")
    public RatingResponse rateBeer(@RequestBody RateRequest rateRequest) {
        double averageRating = ratingService.rate(rateRequest.beerId, rateRequest.rating);
        return new RatingResponse(rateRequest.beerId, averageRating);
    }

    public record RateRequest(long beerId, int rating) {
    }

    public record RatingResponse(long beerId, double averageRating) {

    }
}
