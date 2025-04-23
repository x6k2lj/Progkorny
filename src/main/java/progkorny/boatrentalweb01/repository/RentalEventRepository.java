package progkorny.boatrentalweb01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import progkorny.boatrentalweb01.model.RentalEvent;

public interface RentalEventRepository extends JpaRepository<RentalEvent,Integer> {
}
