package com.productdock.beerrating.repository;

import com.productdock.beerrating.model.BeerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<BeerRating, Long> {

    @Query("SELECT AVG(br.rating) FROM BeerRating br WHERE br.beerId = :beerId")
    double findAverageRatingByBeerId(@Param("beerId") long beerId);
}
