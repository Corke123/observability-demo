package com.productdock.beerrating.config;

import com.productdock.beerrating.client.BeerCatalog;
import com.productdock.beerrating.config.properties.BeerCatalogProperties;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(BeerCatalogProperties.class)
public class RestClientConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder,
                                 BeerCatalogProperties beerCatalogProperties,
                                 ObservationRegistry observationRegistry) {
        return builder
                .observationRegistry(observationRegistry)
                .baseUrl(beerCatalogProperties.baseUrl())
                .build();
    }

    @Bean
    public BeerCatalog beerCatalog(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BeerCatalog.class);
    }
}
