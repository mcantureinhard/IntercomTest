package intercomTest.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Customer {
    private String name;
    private Integer id;
    private Location location;
}
