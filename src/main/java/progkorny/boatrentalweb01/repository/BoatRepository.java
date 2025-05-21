package progkorny.boatrentalweb01.repository;

import progkorny.boatrentalweb01.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
}
