package intercomTest.application.usecases;

import intercomTest.application.services.OfficeLocations;
import intercomTest.domain.Configuration;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.io.File;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
class FilteredCustomersUseCaseTest {

    /*
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void clean(){}
     */

    @Test
    void filter() {
        Configuration mockConfiguration = mock(Configuration.class);
        File file = new File("src/test/resources");
        String absolutePath = file.getAbsolutePath();
        assertThrows(RuntimeException.class, () -> new FilteredCustomersUseCase(mockConfiguration, new OfficeLocations()));
        when(mockConfiguration.get(anyString())).thenReturn(absolutePath + "/CustomersTestFile.txt");
        FilteredCustomersUseCase useCase = new FilteredCustomersUseCase(mockConfiguration, new OfficeLocations());
        assertEquals(0, useCase.filter("unknown", 1000, false).size());
        assertEquals(0, useCase.filter("london", 1, false).size());
        assertEquals(32, useCase.filter("dublin", 1000, false).size());
    }
}