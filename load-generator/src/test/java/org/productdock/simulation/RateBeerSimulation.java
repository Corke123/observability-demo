package org.productdock.simulation;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.http.HeaderNames.Accept;
import static io.gatling.http.HeaderNames.ContentType;
import static io.gatling.http.HeaderValues.ApplicationJson;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.http;

public class RateBeerSimulation extends Simulation {

    final Duration duration = Duration.ofMinutes(30);
    final int usersPerSecond = 5;

    final HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080")
            .contentTypeHeader(ApplicationJson())
            .acceptHeader(ApplicationJson());

    final ChainBuilder rateRandomBeer = exec(session -> session
            .set("beerId", generateRandomBeerId())
            .set("rating", generateRandomRating())
    ).exec(http("rateRandomBeer")
            .post(session -> "/ratings")
            .body(StringBody("""
                        {
                          "beerId": #{beerId},
                          "rating": #{rating}
                        }
                    """))
            .asJson()
            .header(ContentType(), ApplicationJson())
            .header(Accept(), ApplicationJson())
    );

    int generateRandomBeerId() {
        double random = Math.random();
        if (random < 0.1) return 4;
        else if (random < 0.7) return 3;
        else if (random < 0.9) return 2;
        else return 1;
    }

    int generateRandomRating() {
        return (int) (Math.random() * 5) + 1;
    }

    {
        System.out.println(("duration: %s".formatted(duration)));
        System.out.println(("constantUsersPerSec: %d".formatted(usersPerSecond)));
        setUp(scenario("steepTea")
                .exec(rateRandomBeer)
                .injectOpen(constantUsersPerSec(usersPerSecond).during(duration))
        ).protocols(httpProtocol);
    }
}
