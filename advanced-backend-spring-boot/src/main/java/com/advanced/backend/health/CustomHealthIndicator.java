package com.advanced.backend.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean healthy = checkCustomCondition();
        return healthy ? Health.up().build() : Health.down().withDetail("error", "Something's wrong").build();
    }

    private boolean checkCustomCondition() {
        return true; // Add your custom check here
    }
}
