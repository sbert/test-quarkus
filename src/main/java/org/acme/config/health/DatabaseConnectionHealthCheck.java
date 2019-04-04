package org.acme.config.health;

import org.acme.config.Person;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

@Health
@ApplicationScoped
public class DatabaseConnectionHealthCheck implements HealthCheck {


    @Inject
    EntityManager em;

    @Override
    public HealthCheckResponse call() {

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database connection health check");

        try {
            em.createNativeQuery("SELECT 1").getResultList();
            responseBuilder.up();
        } catch (IllegalStateException | PersistenceException e) {
            // cannot access the database
            responseBuilder.down()
                    .withData("error", e.getMessage());
        }

        return responseBuilder.build();
    }

}