package com.productdock.beerservice.beer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    @Id
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String name;

    private String description;

    private String origin;

    private double abv;

    private String imageUrl;
}
