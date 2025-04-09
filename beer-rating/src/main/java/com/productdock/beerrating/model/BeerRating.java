package com.productdock.beerrating.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerRating {

    @Id
    @Setter(AccessLevel.PRIVATE)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private long beerId;

    private int rating;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}
