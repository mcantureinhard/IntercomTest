package intercomTest.application.services;

import intercomTest.domain.Location;
import lombok.Value;

import java.util.Map;

@Value
public class OfficeLocations {
    // Just some office locations, no interface, but can be refactored if wanted
    private final Map<String, Location> intercomOfficeLocations = Map.ofEntries(
            Map.entry("san francisco", Location.builder().latitude(37.812516).longitude(-122.464962).build()),
            Map.entry("london", Location.builder().latitude(51.500342).longitude(-0.123067).build()),
            Map.entry("dublin", Location.builder().latitude(53.351052).longitude(-6.245724).build()),
            Map.entry("chicago", Location.builder().latitude(41.883092).longitude(-87.605958).build()),
            Map.entry("sydney", Location.builder().latitude(-33.857192).longitude(151.216042).build())
    );
}
