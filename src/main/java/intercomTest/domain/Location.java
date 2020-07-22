package intercomTest.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Location {
    private static final Double R = 6371d;
    private Double latitude;
    private Double longitude;

    public Double orthodromicDistance(Location location){
        Double selfLatRad = Math.toRadians(latitude);
        Double selfLonRad = Math.toRadians(longitude);
        Double pointLatRad = Math.toRadians(location.getLatitude());
        Double pointLonRad = Math.toRadians(location.getLongitude());

        return Math.acos(
                Math.sin(selfLatRad)
                        * Math.sin(pointLatRad)
                        + Math.cos(selfLatRad)
                        * Math.cos(pointLatRad)
                        * Math.cos(
                        Math.abs(
                                selfLonRad - pointLonRad
                        )
                )
        ) * R;
    }
}