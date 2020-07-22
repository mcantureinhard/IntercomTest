package intercomTest.infrastructure.configuration;

import intercomTest.domain.Configuration;

public class EnvironmentConfiguration implements Configuration {
    // The implementation for the configuration for this task comes from an env variable since we only have one entry
    @Override
    public String get(String key) {
        return System.getenv().get(key);
    }
}
