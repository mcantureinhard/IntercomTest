package intercomTest.domain;

import java.util.stream.Stream;

public interface CustomerStream {
    Stream<Customer> getStream(int batchSize);
}
