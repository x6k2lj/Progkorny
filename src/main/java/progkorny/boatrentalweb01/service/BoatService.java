package progkorny.boatrentalweb01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progkorny.boatrentalweb01.model.Boat;
import progkorny.boatrentalweb01.repository.BoatRepository;

import java.util.List;
import java.util.Optional;

@Service  // Service réteg komponensként jelöli az osztályt, a Spring kezeli az életciklust
public class BoatService {

    @Autowired  // Automatikusan beszúrja a BoatRepository példányt (dependency injection)
    private BoatRepository boatRepository;

    // Összes hajó lekérdezése az adatbázisból
    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    // Egy hajó lekérdezése ID alapján, Optional típusban visszatérve (lehet üres)
    public Optional<Boat> getBoatById(Long id) {
        return boatRepository.findById(id);
    }

    // Új hajó létrehozása és mentése az adatbázisba
    public Boat createBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    @Transactional  // Tranzakciókezelés: az egész metódus vagy végrehajtódik, vagy nem
    public Boat updateBoat(Long id, Boat updatedBoat) {
        // Megkeressük az adott ID-jű hajót, ha nincs, RuntimeException dobódik
        Boat existing = boatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boat not found"));

        // Optimistic Locking: a @Version mező használatával automatikus versenykezelés a frissítések között

        // A létező hajó adatainak frissítése az új adatokkal
        existing.setName(updatedBoat.getName());
        existing.setBrand(updatedBoat.getBrand());
        existing.setLength(updatedBoat.getLength());
        existing.setModel(updatedBoat.getModel());
        existing.setBuildYear(updatedBoat.getBuildYear());
        existing.setDailyRate(updatedBoat.getDailyRate());
        existing.setAvailable(updatedBoat.isAvailable());
        existing.setNumberOfSeats(updatedBoat.getNumberOfSeats());
        existing.setVersion(updatedBoat.getVersion()); // Verzió mező frissítése, hogy az optimista zárolás működjön

        // Mentés az adatbázisba, frissített entitás visszaadása
        return boatRepository.save(existing);
    }

    // Hajó törlése ID alapján, visszatérési érték jelzi, sikerült-e a törlés
    public boolean deleteBoat(Long id) {
        if (boatRepository.existsById(id)) {  // Ellenőrizzük, hogy létezik-e a hajó
            boatRepository.deleteById(id);     // Törlés adatbázisból
            return true;                       // Törlés sikeres
        } else {
            return false;                      // Nem találtuk a hajót, törlés nem történt
        }
    }
}
