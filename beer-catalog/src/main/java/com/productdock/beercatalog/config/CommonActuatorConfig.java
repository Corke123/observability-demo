package com.productdock.beercatalog.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationPredicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.observation.ServerRequestObservationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.ServerHttpObservationFilter;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Configuration(proxyBeanMethods = false)
public class CommonActuatorConfig {

    @Bean
    ObservationPredicate noActuatorServerObservations() {
        return (name, context) -> {
            if (name.equals("http.server.requests") && context instanceof ServerRequestObservationContext serverContext) {
                return !serverContext.getCarrier().getRequestURI().startsWith("/actuator");
            }
            else {
                return true;
            }
        };
    }

    @Bean
    ObservationPredicate noRootlessHttpObservations() {
        return (name, context) -> {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (requestAttributes instanceof ServletRequestAttributes) {
                Observation observation = (Observation) requestAttributes.getAttribute(ServerHttpObservationFilter.class.getName() + ".observation", SCOPE_REQUEST);
                return observation == null || !observation.isNoop();
            }
            else {
                return true;
            }
        };
    }

}