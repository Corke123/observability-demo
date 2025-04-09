package com.productdock.beercatalog.controller;

import com.productdock.beercatalog.model.Beer;
import com.productdock.beercatalog.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping("beers")
    public List<Beer> getBeers() {
        return beerService.getAll();
    }

    @GetMapping("beers/{beerId}")
    public Beer getBeer(@PathVariable long beerId) {
        return beerService.getById(beerId);
    }

}
