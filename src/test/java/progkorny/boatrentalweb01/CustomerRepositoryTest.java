package progkorny.boatrentalweb01.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import progkorny.boatrentalweb01.model.Customer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveAndFindCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Béla");
        customer.setLastName("Kiss");
        customer.setEmail("bela.kiss@example.com");
        customer.setPhoneNumber("+3611223344");
        customer.setDriverLicenseNumber("DL112233");
        customer.setCountryCode("HU");

        customerRepository.save(customer);

        Integer id = customer.getId();  // ID-t most kapja meg az adatbázisból

        Optional<Customer> found = customerRepository.findById(id);

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("Béla");
        assertThat(found.get().getEmail()).isEqualTo("bela.kiss@example.com");
    }

    @Test
    void deleteCustomer_shouldRemoveFromDb() {
        Customer customer = new Customer();
        customer.setFirstName("Tom");
        customer.setLastName("Papp");
        customer.setEmail("tom.papp@ezmegaz.com");
        customer.setPhoneNumber("+3630111222");
        customer.setDriverLicenseNumber("DL334455");
        customer.setCountryCode("HU");

        customerRepository.save(customer);

        Integer id = customer.getId();

        customerRepository.deleteById(id);

        Optional<Customer> deleted = customerRepository.findById(id);

        assertThat(deleted).isEmpty();
    }
}
