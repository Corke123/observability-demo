package com.productdock.beerservice.beer;

import com.productdock.beerservice.beer.exception.BeerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerService {

    private final BeerRepository beerRepository;

    public Beer getById(long id) {
        log.info("Getting beer by id {}", id);
        return beerRepository.findById(id)
                .orElseThrow(() -> new BeerNotFoundException("Unable to find Beer with id: " + id));
    }

    public List<Beer> getAll() {
        log.info("Getting all beers");
        return beerRepository.findAll();
    }
}
