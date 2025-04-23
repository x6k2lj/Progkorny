package progkorny.boatrentalweb01.repository;

import progkorny.boatrentalweb01.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
