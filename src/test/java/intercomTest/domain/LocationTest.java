package intercomTest.domain;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void orthodromicDistance() {
        Location dublin = Location.builder().latitude(53.351052).longitude(-6.245724).build();
        Location zurich = Location.builder().latitude(47.377415).longitude(8.542422).build();
        Double distanceDublinZurich = dublin.orthodromicDistance(zurich);
        assertTrue(distanceDublinZurich > 1237 && distanceDublinZurich < 1239);
    }
}