package intercomTest.infrastructure.customerFileLoader;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerRow {
    // I decided to use different classes for the Customer that we use in the application and the Customer row in the file.
    private String name;
    @SerializedName("user_id")
    private Integer userId;
    private Double latitude;
    private Double longitude;
}
