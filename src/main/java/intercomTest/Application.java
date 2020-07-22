package intercomTest;

import intercomTest.application.services.OfficeLocations;
import intercomTest.application.usecases.FilteredCustomersUseCase;
import intercomTest.domain.Customer;
import intercomTest.infrastructure.configuration.EnvironmentConfiguration;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;

@CommandLine.Command(name = "CustomerSearch", mixinStandardHelpOptions = true, version = "CusomerSearch 1.0",
        description = "Prints customers that are within a certain distance of one of the office locations")
public class Application implements Runnable {

    @Option(names = { "-d", "--descending" }, description = "Sort by descending id. Default is ascending sorting ")
    private boolean desc = false;

    @Parameters(index = "0", paramLabel = "CITY", description = "Office location")
    private String office;

    @Parameters(index = "1", paramLabel = "MAX_DISTANCE_KM", description = "Maximum desired distance in KM to the office city")
    private Integer distance;

    public void run() {
        try {
            FilteredCustomersUseCase useCase = new FilteredCustomersUseCase(new EnvironmentConfiguration(), new OfficeLocations());
            List<Customer> customerList = useCase.filter(office,distance, desc);
            System.out.println(customerList.size() + " customers within " + distance + " Km of the " + StringUtils.capitalize(office) + " office.");
            if(!customerList.isEmpty()){
                System.out.println("------------------------------------------------");
                for(Customer customer: customerList){
                    System.out.println(customer.getId() + " - " + customer.getName());
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new CommandLine(new Application()).execute(args);
    }
}
