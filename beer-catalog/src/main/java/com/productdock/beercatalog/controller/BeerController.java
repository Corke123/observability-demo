package com.productdock.beercatalog.controller;

import com.productdock.beercatalog.model.Beer;
import com.productdock.beercatalog.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    public record BeerResponse(long id, String name, String description, String origin, double abv, String imageUrl) {
    }

    public record BeerValidationResponse(long id, String name) {
    }

    @GetMapping("beers")
    public List<BeerResponse> getBeers() {
        return beerService.getAll().stream()
                .map(BeerController::toBeerResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("beers/{beerId}/validate")
    public BeerValidationResponse getBeer(@PathVariable long beerId) {
        Beer beer = beerService.getById(beerId);
        return new BeerValidationResponse(beer.getId(), beer.getName());
    }

    private static BeerResponse toBeerResponse(Beer beer) {
        return new BeerResponse(beer.getId(), beer.getName(), beer.getDescription(), beer.getOrigin(), beer.getAbv(), beer.getImageUrl());
    }
}
