package intercomTest.domain;

public interface Configuration {
    // Using a configuration interface to give flexibility
    String get(String key);
}
