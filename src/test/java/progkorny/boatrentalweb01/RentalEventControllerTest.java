package progkorny.boatrentalweb01.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import progkorny.boatrentalweb01.model.Customer;
import progkorny.boatrentalweb01.model.RentalEvent;
import progkorny.boatrentalweb01.service.RentalEventService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalEventController.class)
public class RentalEventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalEventService rentalEventService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllRentalEvents() throws Exception {
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");

        RentalEvent event1 = new RentalEvent();
        event1.setId(1);
        event1.setRentalCustomer(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");

        RentalEvent event2 = new RentalEvent();
        event2.setId(2);
        event2.setRentalCustomer(customer2);

        List<RentalEvent> mockEvents = Arrays.asList(event1, event2);

        Mockito.when(rentalEventService.getAllRentalEvents()).thenReturn(mockEvents);

        mockMvc.perform(get("/api/rentalevents"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockEvents)));
    }
}
