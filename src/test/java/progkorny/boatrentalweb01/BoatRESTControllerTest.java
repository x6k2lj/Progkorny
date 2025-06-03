package progkorny.boatrentalweb01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import progkorny.boatrentalweb01.model.Customer;
import progkorny.boatrentalweb01.repository.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoatRESTControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@BeforeEach
	void setup() {
		customerRepository.deleteAll();
	}

	@Test
	void testCreateGetAndDeleteCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Anna");
		customer.setLastName("Nagy");
		customer.setEmail("anna.nagy@example.com");
		customer.setPhoneNumber("+3612345678");
		customer.setDriverLicenseNumber("DL998877");
		customer.setCountryCode("HU");

		// POST
		ResponseEntity<Customer> postResponse = restTemplate.postForEntity("/api/customers", customer, Customer.class);
		assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		Customer createdCustomer = postResponse.getBody();
		assertThat(createdCustomer).isNotNull();
		Integer id = createdCustomer.getId();
		assertThat(id).isNotNull();

		// GET
		ResponseEntity<Customer> getResponse = restTemplate.getForEntity("/api/customers/{id}", Customer.class, id);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(getResponse.getBody()).isNotNull();
		assertThat(getResponse.getBody().getEmail()).isEqualTo("anna.nagy@example.com");

		// DELETE
		restTemplate.delete("/api/customers/{id}", id);

		// GET törlés után - várható 404
		ResponseEntity<Customer> getAfterDelete = restTemplate.getForEntity("/api/customers/{id}", Customer.class, id);
		assertThat(getAfterDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
}
