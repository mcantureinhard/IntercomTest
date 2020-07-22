package intercomTest.application.usecases;

import intercomTest.application.services.OfficeLocations;
import intercomTest.domain.Configuration;
import intercomTest.domain.Customer;
import intercomTest.domain.CustomerStream;
import intercomTest.domain.Location;
import intercomTest.infrastructure.customerFileLoader.CustomerFileStream;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FilteredCustomersUseCase {
    private OfficeLocations officeLocations;
    private CustomerStream customerStream;
    Configuration configuration;

    // Our use case receives the implementations of the interface it works with. With spring we could have used autowired
    // But for this it seemed too much to use spring
    public FilteredCustomersUseCase(
            Configuration configuration,
            OfficeLocations officeLocations
    ) throws RuntimeException{
        this.officeLocations = officeLocations;
        this.configuration = configuration;
        try {
            String customersFile = configuration.get("CUSTOMER_FILE");
            if(customersFile == null){
                throw new RuntimeException("Please set environmental variable CUSTOMER_FILE pointing to the customer file");
            }
            customerStream = new CustomerFileStream(customersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> filter(String city, Integer distance, boolean desc) {

        Location officeLocation = officeLocations.getIntercomOfficeLocations().get(city.toLowerCase());
        if(officeLocation == null){
            System.out.println("Invalid office location.\nValid locations are:\n");
            officeLocations.getIntercomOfficeLocations().keySet().stream().forEach(s -> System.out.println(StringUtils.capitalize(s)));
            return new ArrayList<>();
        }
        // If we multiply the output of compare by -1 we can revert the sorting order
        int reverse = desc ? -1 : 1;
        return customerStream
                .getStream(1)
                .filter(customer -> customer.getLocation().orthodromicDistance(officeLocation) <= distance)
                .sorted((c1, c2) -> reverse * c1.getId().compareTo(c2.getId()))
                .collect(Collectors.toList());
    }

}
