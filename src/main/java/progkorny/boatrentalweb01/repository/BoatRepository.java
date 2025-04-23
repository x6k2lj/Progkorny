package progkorny.boatrentalweb01.repository;

import progkorny.boatrentalweb01.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat,Integer> {
    void deleteBoatById(int id);
}

