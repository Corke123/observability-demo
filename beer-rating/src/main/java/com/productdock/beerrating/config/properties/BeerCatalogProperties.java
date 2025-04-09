package com.productdock.beerrating.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "beer-ratings.beer-catalog")
public record BeerCatalogProperties(String baseUrl) {
}
