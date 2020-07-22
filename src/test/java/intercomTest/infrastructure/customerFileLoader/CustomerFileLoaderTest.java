package intercomTest.infrastructure.customerFileLoader;

import intercomTest.domain.Customer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFileLoaderTest {
    @Test
    public void customFileLoaderTest(){
        try {
            File file = new File("src/test/resources");
            String absolutePath = file.getAbsolutePath();
            CustomerFileStream customerFileLoader = new CustomerFileStream(absolutePath + "/CustomersTestFile.txt");
            List<Customer> customers = customerFileLoader.getStream(1).collect(Collectors.toList());
            assertEquals(32, customers.size());
            assertEquals(customers.get(0).getName(), "Christina McArdle");
            assertEquals(customers.get(9).getName(), "Theresa Enright");
            assertEquals(customers.get(19).getName(), "Ian Larkin");

            assertEquals(customers.get(0).getId(), 12);
            assertEquals(customers.get(9).getId(), 6);
            assertEquals(customers.get(19).getId(), 16);

            assertNotNull(customers.get(0).getLocation());
            assertNotNull(customers.get(9).getLocation());
            assertNotNull(customers.get(19).getLocation());

            assertEquals(customers.get(0).getLocation().getLatitude(), 52.986375);
            assertEquals(customers.get(9).getLocation().getLatitude(), 53.1229599);
            assertEquals(customers.get(19).getLocation().getLatitude(), 52.366037);

            assertEquals(customers.get(0).getLocation().getLongitude(), -6.043701);
            assertEquals(customers.get(9).getLocation().getLongitude(), -6.2705202);
            assertEquals(customers.get(19).getLocation().getLongitude(), -8.179118);
        } catch (Exception e){
            e.printStackTrace();
            assert(false);
        }
    }
    @Test
    public void invalidBatchSize(){
        File file = new File("src/test/resources");
        String absolutePath = file.getAbsolutePath();
        try {
            final CustomerFileStream customerFileLoader = new CustomerFileStream(absolutePath + "/CustomersTestFile.txt");
            assertThrows(IllegalArgumentException.class, () -> customerFileLoader.getStream(0).collect(Collectors.toList()));
        } catch (Exception e){
            e.printStackTrace();
            assert(false);
        }
    }
}