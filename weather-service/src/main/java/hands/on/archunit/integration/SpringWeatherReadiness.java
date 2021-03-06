package hands.on.archunit.integration;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component(value = "readinessCheck")
public class SpringWeatherReadiness implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("ready", true).build();
    }
}
