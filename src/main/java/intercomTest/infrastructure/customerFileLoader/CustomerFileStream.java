package intercomTest.infrastructure.customerFileLoader;

import com.google.gson.Gson;
import intercomTest.domain.Customer;
import intercomTest.domain.CustomerStream;
import intercomTest.domain.Location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

//Originally thought of the CustomerStream interface as the interface to load customer data in memory from different sources.
//Then I realized that was a bit too much for this exercise, but I decided to use what I have since it also works for this usecase
public class CustomerFileStream implements CustomerStream {
    private BufferedReader bufferedReader;
    private final String file;

    public CustomerFileStream(String file) throws IOException {
        this.file = file;
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    @Override
    public Stream<Customer> getStream(int batchSize) {
        return Stream
                .generate(() -> next(batchSize))
                .takeWhile(not(List::isEmpty))
                .flatMap(List::stream);
    }

    private List<Customer> next(int batchSize){
        if(batchSize < 1){
            throw new IllegalArgumentException("Batch size must be at least 1");
        }
        List<Customer> customerList = new ArrayList<>();
        try {
            while(batchSize > 0) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    bufferedReader = new BufferedReader(new FileReader(file));
                    break;
                } else {
                    CustomerRow cr = new Gson().fromJson(line, CustomerRow.class);
                    customerList.add(
                            Customer.builder()
                                    .name(cr.getName())
                                    .id(cr.getUserId())
                                    .location(
                                            Location.builder()
                                                    .latitude(cr.getLatitude())
                                                    .longitude(cr.getLongitude())
                                                    .build())
                                    .build()
                    );
                    batchSize--;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return customerList;
    }
}
