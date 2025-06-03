package progkorny.boatrentalweb01.repository;

import progkorny.boatrentalweb01.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// A @Repository annotációval jelezzük, hogy ez az osztály egy Spring Data Repository,
// amely az adatbázis műveletekért felelős a Boat entitás számára.
@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {
    // A JpaRepository biztosítja az alapvető CRUD műveleteket (Create, Read, Update, Delete)
    // és további hasznos metódusokat.
    // A paraméterek:
    // - Boat: az entitás típusa, amellyel dolgozunk
    // - Long: az entitás azonosítójának típusa (Boat.id típusa)
}
